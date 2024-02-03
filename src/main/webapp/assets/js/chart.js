// web component
class HelloWorld extends HTMLElement {

    constructor() {
        super();

    }
    convertToUnixTimestamp(array) {
        return array.map(obj => {
            const dateObject = new Date(obj.x);
            const timestamp = dateObject.getTime();
            return [timestamp, obj.y];
        });
    }
    convertData() {
        const data = [];
        console.log(this.getAttribute("data"))
        var jsonString = this.getAttribute("data").replace(/(\d{4}-\d{2}-\d{2})/g, '"$1"');
        var arrayOfArrays = null
        try{
            arrayOfArrays = JSON.parse(jsonString);
        } catch (error) {
            jsonString = this.getAttribute("data").replace(/(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2})/g, '"$1"');
            arrayOfArrays = JSON.parse(jsonString)
        }

        //const arrayOfStringDates = arrayOfArrays.map(([date, value]) => [String(date), value]);
        arrayOfArrays.forEach((array) => {
            data.push({
                x: array[0],
                y: array[1]
            })
        })
        var sortedData = data.sort(function (a,b) {
            return new Date(b.x) - new Date(a.x)
        })
        const finalData = this.convertToUnixTimestamp(sortedData)
        return finalData
    }
    createChart() {
        const data = this.convertData()
        const Options = {
            series: [{
                data: data
            }],
            chart: {
                id: 'area-datetime',
                type: 'area',
                height: 350,
                zoom: {
                    autoScaleYaxis: true
                }
            },
            dataLabels: {
                enabled: false
            },
            markers: {
                size: 0,
                style: 'hollow',
            },
            xaxis: {
                type: 'datetime',
                tickAmount: 6,
            },
            tooltip: {
                x: {
                    format: 'dd MMM yyyy'
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shadeIntensity: 1,
                    opacityFrom: 0.7,
                    opacityTo: 0.9,
                    stops: [0, 100]
                }
            },
        };
        return Options
    }
    convertData2() {
        const data = this.getAttribute("financialData")
        const jsonString = data.replace(/(\d+Q\d{4}|\d+\.\d+[BM])/g, '"$1"');
// Parse the preprocessed string into an array of arrays
        const resultArray = JSON.parse(jsonString);
        const modifiedArray = resultArray.map(subArray => subArray.map(item => {
            if (subArray[0] !== item) {
                const value = parseFloat(item);
                return isNaN(value) ? 0 : value;
            }
            return item;
        }));
        return modifiedArray
    }
    creatChart2() {
        const data = this.convertData2();
        const date = data.map(subArray => subArray[0]).slice(0,-1);
        const revenue = data.map(subArray => subArray[1]).slice(0,-1);
        const earnings = data.map(subArray => subArray[2]);

        var options = {
            series: [{
                name: 'Net Profit',
                data: earnings
            }, {
                name: 'Revenue',
                data: revenue
            }],
            chart: {
                type: 'bar',
                height: 350
            },
            plotOptions: {
                bar: {
                    horizontal: false,
                    columnWidth: '55%',
                    endingShape: 'rounded'
                },
            },
            dataLabels: {
                enabled: false
            },
            stroke: {
                show: true,
                width: 2,
                colors: ['transparent']
            },
            xaxis: {
                categories: date,
            },
            fill: {
                opacity: 1
            },

        };
        return options
    }
    getGradient(ctx, chartArea, data, scales) {
        const {left, right, top, bottom, width, hieght} = chartArea
        const {x, y} = scales;
        const gradientBorder = ctx.createLinearGradient(0,0, 0, bottom)
        const shift = y.getPixelForValue(data.datasets[0].data[0].y) / bottom;
        console.log(shift)
        console.log(y)
    }
    // connect component
    connectedCallback() {
        console.log(this.getAttribute("data"))

        const totalRevenueChartEl = document.querySelector('#totalRevenueChart')
        const totalRevenueChart = new ApexCharts(totalRevenueChartEl, this.createChart());
        totalRevenueChart.render();


        const second_chart = document.querySelector('#second_chart')
        var chart = new ApexCharts(second_chart, this.creatChart2());
        chart.render();
    }

}

// register component
customElements.define( 'stock-chart', HelloWorld );