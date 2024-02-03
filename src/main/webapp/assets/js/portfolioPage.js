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
        const datesString = this.getAttribute("datesData");
        const numbersString = this.getAttribute("data");
        const datesArray = datesString.match(/\[(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2})/g).map(entry => {
            const [date] = entry.slice(1).split(',').map(item => item.trim());
            return date;
        });

// Extract numbers using regex
        const numbersArray = numbersString.match(/\d+\.\d+/g).map(Number);

        // Ensure the arrays are of the same length
        const minLength = Math.min(datesArray.length, numbersArray.length);

// Create an array of objects with paired dates and numbers
        const pairedArray = [];
        for (let i = 0; i < minLength; i++) {
            pairedArray.push([
                datesArray[i],
                numbersArray[i]
            ]);
        }
        return pairedArray
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
        const data = this.creatChart2();
        const date = data.map(subArray => subArray[0]);
        const revenue = data.map(subArray => subArray[1]);
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
            tooltip: {
                y: {
                    formatter: function (val) {
                        return "$ " + val + " thousands"
                    }
                }
            }
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
        console.log(this.convertData())

        const totalRevenueChartEl = document.querySelector('#totalRevenueChart')
        const totalRevenueChart = new ApexCharts(totalRevenueChartEl, this.createChart());
        totalRevenueChart.render();





    }

}

// register component
customElements.define( 'stock-chart', HelloWorld );