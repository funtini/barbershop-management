import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import PageHeader from '../../common/template/content/pageHeader'
import Row from '../../common/layout/row'
import Box from '../../common/elements/box'
import Chart from 'chart.js';


export default class Charts extends Component {

 componentDidMount(){

    // //--------------
    // //- MY CHART -
     // //--------------
            var ctx = $("#myChart");
            var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
            datasets: [{
                label: '# of Votes',
                data: [12, 19, 3, 5, 2, 3],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            }
        }
        });
// //--------------
// //- AREA CHART -
// //--------------~

var areaChartData = {
            
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
        {
          label               : 'Electronics',
          fillColor           : 'rgba(210, 214, 222, 1)',
          strokeColor         : 'rgba(210, 214, 222, 1)',
          pointColor          : 'rgba(210, 214, 222, 1)',
          pointStrokeColor    : '#c1c7d1',
          pointHighlightFill  : '#fff',
          pointHighlightStroke: 'rgba(220,220,220,1)',
          data                : [65, 59, 80, 81, 56, 55, 40]
        },
        {
          label               : 'Digital Goods',
          fillColor           : 'rgba(60,141,188,0.9)',
          strokeColor         : 'rgba(60,141,188,0.8)',
          pointColor          : '#3b8bba',
          pointStrokeColor    : 'rgba(60,141,188,1)',
          pointHighlightFill  : '#fff',
          pointHighlightStroke: 'rgba(60,141,188,1)',
          data                : [28, 48, 40, 19, 86, 27, 90]
        }
      ]
}

var areaChartOptions ={
    //Boolean - If we should show the scale at all
    showScale               : true,
    //Boolean - Whether grid lines are shown across the chart
    scaleShowGridLines      : false,
    //String - Colour of the grid lines
    scaleGridLineColor      : 'rgba(0,0,0,.05)',
    //Number - Width of the grid lines
    scaleGridLineWidth      : 1,
    //Boolean - Whether to show horizontal lines (except X axis)
    scaleShowHorizontalLines: true,
    //Boolean - Whether to show vertical lines (except Y axis)
    scaleShowVerticalLines  : true,
    //Boolean - Whether the line is curved between points
    bezierCurve             : true,
    //Number - Tension of the bezier curve between points
    bezierCurveTension      : 0.3,
    //Boolean - Whether to show a dot for each point
    pointDot                : false,
    //Number - Radius of each point dot in pixels
    pointDotRadius          : 4,
    //Number - Pixel width of point dot stroke
    pointDotStrokeWidth     : 1,
    //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
    pointHitDetectionRadius : 20,
    //Boolean - Whether to show a stroke for datasets
    datasetStroke           : true,
    //Number - Pixel width of dataset stroke
    datasetStrokeWidth      : 2,
    //Boolean - Whether to fill the dataset with a color
    datasetFill             : true,
    //String - A legend template
    legendTemplate          : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].lineColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
    //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
    maintainAspectRatio     : true,
    //Boolean - whether to make the chart responsive to window resizing
    responsive              : true
}

 var ctx = $("#areaChart");
            var myChart = new Chart(ctx, {
                type: 'bar',
        data: areaChartData,
        options:areaChartOptions 
        });
// // //--------------
// // //- LINE CHART -
// // //--------------
// //Create the line chart
// areaChart.Line(areaChartData, areaChartOptions)

//-------------
//- LINE CHART -
//--------------
var lineChartCanvas          = $('#lineChart').get(0).getContext('2d')
var lineChart                = new Chart(lineChartCanvas, {
    type: 'line',
    data: areaChartData,
    options: areaChartOptions 

})

//-------------
    //- PIE CHART -
    //-------------
    // Get context with jQuery - using jQuery's .get() method.
    var PieData        = [
        {
          value    : 700,
          color    : '#f56954',
          highlight: '#f56954',
          label    : 'Chrome'
        },
        {
          value    : 500,
          color    : '#00a65a',
          highlight: '#00a65a',
          label    : 'IE'
        },
        {
          value    : 400,
          color    : '#f39c12',
          highlight: '#f39c12',
          label    : 'FireFox'
        },
        {
          value    : 600,
          color    : '#00c0ef',
          highlight: '#00c0ef',
          label    : 'Safari'
        },
        {
          value    : 300,
          color    : '#3c8dbc',
          highlight: '#3c8dbc',
          label    : 'Opera'
        },
        {
          value    : 100,
          color    : '#d2d6de',
          highlight: '#d2d6de',
          label    : 'Navigator'
        }
      ]
      var pieOptions     = {
        //Boolean - Whether we should show a stroke on each segment
        segmentShowStroke    : true,
        //String - The colour of each segment stroke
        segmentStrokeColor   : '#fff',
        //Number - The width of each segment stroke
        segmentStrokeWidth   : 2,
        //Number - The percentage of the chart that we cut out of the middle
        percentageInnerCutout: 0, // This is 0 for Pie charts
        //Number - Amount of animation steps
        animationSteps       : 100,
        //String - Animation easing effect
        animationEasing      : 'easeOutBounce',
        //Boolean - Whether we animate the rotation of the Doughnut
        animateRotate        : true,
        //Boolean - Whether we animate scaling the Doughnut from the centre
        animateScale         : false,
        //Boolean - whether to make the chart responsive to window resizing
        responsive           : true,
        // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
        maintainAspectRatio  : true,
        //String - A legend template
        legendTemplate       : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<segments.length; i++){%><li><span style="background-color:<%=segments[i].fillColor%>"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>'
      }
    var pieChartCanvas = $('#pieChart').get(0).getContext('2d')
    var pieChart       = new Chart(pieChartCanvas,{
        type: 'pie',
        data: PieData,
        options: pieOptions

    })
    
//     //-------------
//     //- DONUT CHART - [ FLOT CHAR ] 
//     //-------------
//     // 
//     // We use an inline data source in the example, usually data would
//     // be fetched from a server
//     var data = [], totalPoints = 100

//     function getRandomData() {
// console.log("FUNCIONA")
//       if (data.length > 0)
//         data = data.slice(1)

//       // Do a random walk
//       while (data.length < totalPoints) {

//         var prev = data.length > 0 ? data[data.length - 1] : 50,
//             y    = prev + Math.random() * 10 - 5

//         if (y < 0) {
//           y = 0
//         } else if (y > 100) {
//           y = 100
//         }

//         data.push(y)
//       }

//       // Zip the generated y values with the x values
//       var res = []
//       for (var i = 0; i < data.length; ++i) {
//         res.push([i, data[i]])
//       }

//       return res
//     }

//     var interactive_plot = $.plot('#interactive', [getRandomData()], {
//       grid  : {
//         borderColor: '#f3f3f3',
//         borderWidth: 1,
//         tickColor  : '#f3f3f3'
//       },
//       series: {
//         shadowSize: 0, // Drawing is faster without shadows
//         color     : '#3c8dbc'
//       },
//       lines : {
//         fill : true, //Converts the line chart to area chart
//         color: '#3c8dbc'
//       },
//       yaxis : {
//         min : 0,
//         max : 100,
//         show: true
//       },
//       xaxis : {
//         show: true
//       }
//     })

//     var updateInterval = 500 //Fetch data ever x milliseconds
//     var realtime       = 'on' //If == to on then fetch data every x seconds. else stop fetching
//     function update() {

//       interactive_plot.setData([getRandomData()])

//       // Since the axes don't change, we don't need to call plot.setupGrid()
//       interactive_plot.draw()
//       if (realtime === 'on')
//         setTimeout(update, updateInterval)
//     }

//     //INITIALIZE REALTIME DATA FETCHING
//     if (realtime === 'on') {
//       update()
//     }
//     //REALTIME TOGGLE
//     $('#realtime .btn').click(function () {
//       if ($(this).data('toggle') === 'on') {
//         realtime = 'on'
//       }
//       else {
//         realtime = 'off'
//       }
//       update()
//     })
//     /*
//      * END INTERACTIVE CHART
//      */

//     /*
//      * LINE CHART
//      * ----------
//      */
//     //LINE randomly generated data

//     var sin = [], cos = []
//     for (var i = 0; i < 14; i += 0.5) {
//       sin.push([i, Math.sin(i)])
//       cos.push([i, Math.cos(i)])
//     }
//     var line_data1 = {
//       data : sin,
//       color: '#3c8dbc'
//     }
//     var line_data2 = {
//       data : cos,
//       color: '#00c0ef'
//     }
//     $.plot('#line-chart', [line_data1, line_data2], {
//       grid  : {
//         hoverable  : true,
//         borderColor: '#f3f3f3',
//         borderWidth: 1,
//         tickColor  : '#f3f3f3'
//       },
//       series: {
//         shadowSize: 0,
//         lines     : {
//           show: true
//         },
//         points    : {
//           show: true
//         }
//       },
//       lines : {
//         fill : false,
//         color: ['#3c8dbc', '#f56954']
//       },
//       yaxis : {
//         show: true
//       },
//       xaxis : {
//         show: true
//       }
//     })
//     //Initialize tooltip on hover
//     $('<div class="tooltip-inner" id="line-chart-tooltip"></div>').css({
//       position: 'absolute',
//       display : 'none',
//       opacity : 0.8
//     }).appendTo('body')
//     $('#line-chart').bind('plothover', function (event, pos, item) {

//       if (item) {
//         var x = item.datapoint[0].toFixed(2),
//             y = item.datapoint[1].toFixed(2)

//         $('#line-chart-tooltip').html(item.series.label + ' of ' + x + ' = ' + y)
//           .css({ top: item.pageY + 5, left: item.pageX + 5 })
//           .fadeIn(200)
//       } else {
//         $('#line-chart-tooltip').hide()
//       }

//     })
//     /* END LINE CHART */

//     /*
//      * FULL WIDTH STATIC AREA CHART
//      * -----------------
//      */
//     var areaData = [[2, 88.0], [3, 93.3], [4, 102.0], [5, 108.5], [6, 115.7], [7, 115.6],
//       [8, 124.6], [9, 130.3], [10, 134.3], [11, 141.4], [12, 146.5], [13, 151.7], [14, 159.9],
//       [15, 165.4], [16, 167.8], [17, 168.7], [18, 169.5], [19, 168.0]]
//     $.plot('#area-chart', [areaData], {
//       grid  : {
//         borderWidth: 0
//       },
//       series: {
//         shadowSize: 0, // Drawing is faster without shadows
//         color     : '#00c0ef'
//       },
//       lines : {
//         fill: true //Converts the line chart to area chart
//       },
//       yaxis : {
//         show: false
//       },
//       xaxis : {
//         show: false
//       }
//     })

//     /* END AREA CHART */

//     /*
//      * BAR CHART
//      * ---------
//      */

//     var bar_data = {
//       data : [['January', 10], ['February', 8], ['March', 4], ['April', 13], ['May', 17], ['June', 9]],
//       color: '#3c8dbc'
//     }
//     $.plot('#bar-chart', [bar_data], {
//       grid  : {
//         borderWidth: 1,
//         borderColor: '#f3f3f3',
//         tickColor  : '#f3f3f3'
//       },
//       series: {
//         bars: {
//           show    : true,
//           barWidth: 0.5,
//           align   : 'center'
//         }
//       },
//       xaxis : {
//         mode      : 'categories',
//         tickLength: 0
//       }
//     })
//     /* END BAR CHART */

//     /*
//      * DONUT CHART
//      * -----------
//      */

//     var donutData = [
//       { label: 'Series2', data: 30, color: '#3c8dbc' },
//       { label: 'Series3', data: 20, color: '#0073b7' },
//       { label: 'Series4', data: 50, color: '#00c0ef' }
//     ]
//     $.plot('#donut-chart', donutData, {
//       series: {
//         pie: {
//           show       : true,
//           radius     : 1,
//           innerRadius: 0.5,
//           label      : {
//             show     : true,
//             radius   : 2 / 3,
//             formatter: labelFormatter,
//             threshold: 0.1
//           }

//         }
//       },
//       legend: {
//         show: false
//       }
//     })
//     /*
//      * END DONUT CHART
//      */

// }

// /*
//    * Custom Label formatter
//    * ----------------------
//    */
//   labelFormatter(label, series) {
//     return '<div style="font-size:13px; text-align:center; padding:2px; color: #fff; font-weight: 600;">'
//       + label
//       + '<br>'
//       + Math.round(series.percent) + '%</div>'
//   }
  }  

render() {
        
        
        
        return (
            <div>
                <ContentHeader title='Charts' small='version 1.0' />

                <Content>
               


                    <Row>
                        <Box cols="12 4" title="My Chart" icon="edit" primary >
                        <canvas id="myChart" width="400" height="400"></canvas>
                        </Box>
                        <Box cols="12 4" title="Area/Bar Chart" icon="edit" primary >
                        <div className="chart">
                                <canvas id="areaChart" style={{height:"250px"}}></canvas>
                            </div>
                        </Box>


                        <Box cols="12 4" title="Line Charts" icon="edit" primary >
                        <div className="chart">
                            <canvas id="lineChart" style={{height:"250px"}}></canvas>
                        </div>
                        </Box>



                        <Box cols="12 4" title="Bar Charts" icon="warning"> 
                        <div className="chart">
                            <canvas id="pieChart" ></canvas>
                        </div>
                        </Box>
                        <Box cols="12 4" title="Donut Charts" icon="warning"> 
                            <div id="donut-chart" style={{height: "300px"}}></div>
                        </Box>
                        
                    </Row>
                    <PageHeader title="Progress Bars" />
                    
                    <PageHeader title="Accordion & Carousel" />
                    
                
                   
                    <PageHeader />


                </Content>
            </div >
        )
    }
}