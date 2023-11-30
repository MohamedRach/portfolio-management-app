// web component
class HelloWorld extends HTMLElement {

    constructor() {
        super();

    }
    convertData() {
        const data = [];
        const jsonString = this.getAttribute("data").replace(/(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2})/g, '"$1"');
        const arrayOfArrays = JSON.parse(jsonString);
        arrayOfArrays.forEach((array) => {
            data.push({
                x: array[0],
                y: array[1]
            })
        })
        var sortedData = data.sort(function (a,b) {
            return new Date(b.x) - new Date(a.x)
        })
        return sortedData
    }
    createChart() {
        const data = this.convertData()
        const chartConfig = {
            type: 'line',
            data: {
                labels: [],
                datasets: [{
                    data: data,
                    label: "Stock Prices",
                    backgroundColor: [
                        'rgba(255, 26, 104, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)',
                        'rgba(0, 0, 0, 0.2)'
                    ],
                    borderColor: (context) => {
                        const chart = context.chart;
                        const {ctx, chartArea, data, scales} = chart;
                        if(!chartArea){
                            console.log("hhhh")
                            return null
                        }
                        console.log("kkkkkk")
                        return this.getGradient(ctx, chartArea, data, scales)
                    },
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    xAxes: [{
                        type: 'time',
                        distribution: 'linear',
                    }],
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }],
                    title: {
                        display: false,
                    }
                }
            }
        };
        return chartConfig
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
        //console.log(this.getAttribute("data"))

        //console.log(arrayOfArrays);
        const ctx = document.querySelector("#line-chart").getContext('2d');
        new Chart(ctx, this.createChart())
    }

}

// register component
customElements.define( 'stock-chart', HelloWorld );