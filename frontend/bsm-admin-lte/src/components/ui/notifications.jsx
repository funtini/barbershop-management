import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import PageHeader from '../../common/template/content/pageHeader'
import Row from '../../common/layout/row'
import Box from '../../common/elements/box'
import Palette from '../../common/elements/palette'
import SuccessAlert from '../../common/elements/successAlert'
import DangerAlert from '../../common/elements/dangerAlert'
import InfoAlert from '../../common/elements/infoAlert'
import WarningAlert from '../../common/elements/warningAlert'
import Grid from '../../common/layout/grid'
import ProgressBar from '../../common/label/progressBar'
import Button from '../../common/elements/button'
import { Modal, Carousel} from 'react-bootstrap';
// import Chart from 'chart.js';
import {Bar,Line,Pie} from 'react-chartjs-2';


export default class Notifications extends Component {
    constructor(props) {
        super(props);
    
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
    
        this.state = {
          show: false,
          chartData:{
              labels: ['Simple Haircut','Full Haircut','Shave','HairGel','CleanShave','Shave&Haircut'],
              datasets:[
                  {
                      label:'Products',
                      borderCapStyle:'butt',
                      borderJoinStyle:'miter',
                      borderDash:[],
                      borderDashOffset:0.0,
                      data:[
                          251,
                          181,
                          211,
                          30,
                          41,
                          98
                      ],
                      borderWidth: 1,
                        hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
                        hoverBorderColor: 'rgba(91, 163, 245,1)',
                    
                      hoverBorderWidth:2,
                      backgroundColor: 'rgba(91, 163, 245,1)',
                      borderColor: 'rgba(75,192,192,1)'
                  }
              ]
          }
        };
      }

      componentDidMount(){
        
            /* ChartJS
             * -------
             * Here we will create a few charts using ChartJS
             */
            //  var areaChartCanvas = $('#areaChart').get(0).getContext('2d')
            var ctx = $("#areaChart");
            var myChart = new Chart(ctx, {
                type: 'bar',
        data: {
            
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [
                {
                  label               : 'Electronics',
                  fillColor           : 'rgba(0,0,255,0.3)',
                  strokeColor         : 'rgba(0,0,255,0.3)',
                  pointColor          : 'rgba(0,0,255,0.3)',
                  pointStrokeColor    : '#c1c7d1',
                  pointHighlightFill  : '#fff',
                  pointHighlightStroke: 'rgba(220,220,220,1)',
                  data                : [65, 59, 80, 81, 56, 55, 40]
                },
                {
                  label               : 'Digital Goods',
                  fillColor           : 'rgba(0,0,255,0.3)',
                  strokeColor         : 'rgba(0,0,255,0.3)',
                  pointColor          : '#3b8bba',
                  pointStrokeColor    : 'rgba(0,0,255,0.3)',
                  pointHighlightFill  : '#fff',
                  pointHighlightStroke: 'rgba(0,0,255,0.3)',
                  data                : [28, 48, 40, 19, 86, 27, 90]
                }
              ]
        },
        options: {
            //Boolean - If we should show the scale at all
            showScale               : true,
            //Boolean - Whether grid lines are shown across the chart
            scaleShowGridLines      : false,
            //String - Colour of the grid lines
            scaleGridLineColor      : 'rgba(0,0,255,0.3)',
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
        });



        
            // //--------------
            // //- AREA CHART -
            // //--------------
        
            // // Get context with jQuery - using jQuery's .get() method.
            // var areaChartCanvas = $('#areaChart').get(0).getContext('2d')
            // // var areaChartCanvas = $("#areaChart");
            // // This will get the first returned node in the jQuery collection.
            // var areaChart       = new Chart(areaChartCanvas)
        
            // var areaChartData = {
            //   labels  : ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            //   datasets: [
            //     {
            //       label               : 'Electronics',
            //       fillColor           : 'rgba(210, 214, 222, 1)',
            //       strokeColor         : 'rgba(210, 214, 222, 1)',
            //       pointColor          : 'rgba(210, 214, 222, 1)',
            //       pointStrokeColor    : '#c1c7d1',
            //       pointHighlightFill  : '#fff',
            //       pointHighlightStroke: 'rgba(220,220,220,1)',
            //       data                : [65, 59, 80, 81, 56, 55, 40]
            //     },
            //     {
            //       label               : 'Digital Goods',
            //       fillColor           : 'rgba(60,141,188,0.9)',
            //       strokeColor         : 'rgba(60,141,188,0.8)',
            //       pointColor          : '#3b8bba',
            //       pointStrokeColor    : 'rgba(60,141,188,1)',
            //       pointHighlightFill  : '#fff',
            //       pointHighlightStroke: 'rgba(60,141,188,1)',
            //       data                : [28, 48, 40, 19, 86, 27, 90]
            //     }
            //   ]
            // }
        
            // var areaChartOptions = {
            //   //Boolean - If we should show the scale at all
            //   showScale               : true,
            //   //Boolean - Whether grid lines are shown across the chart
            //   scaleShowGridLines      : false,
            //   //String - Colour of the grid lines
            //   scaleGridLineColor      : 'rgba(0,0,0,.05)',
            //   //Number - Width of the grid lines
            //   scaleGridLineWidth      : 1,
            //   //Boolean - Whether to show horizontal lines (except X axis)
            //   scaleShowHorizontalLines: true,
            //   //Boolean - Whether to show vertical lines (except Y axis)
            //   scaleShowVerticalLines  : true,
            //   //Boolean - Whether the line is curved between points
            //   bezierCurve             : true,
            //   //Number - Tension of the bezier curve between points
            //   bezierCurveTension      : 0.3,
            //   //Boolean - Whether to show a dot for each point
            //   pointDot                : false,
            //   //Number - Radius of each point dot in pixels
            //   pointDotRadius          : 4,
            //   //Number - Pixel width of point dot stroke
            //   pointDotStrokeWidth     : 1,
            //   //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
            //   pointHitDetectionRadius : 20,
            //   //Boolean - Whether to show a stroke for datasets
            //   datasetStroke           : true,
            //   //Number - Pixel width of dataset stroke
            //   datasetStrokeWidth      : 2,
            //   //Boolean - Whether to fill the dataset with a color
            //   datasetFill             : true,
            //   //String - A legend template
            //   legendTemplate          : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].lineColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
            //   //Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
            //   maintainAspectRatio     : true,
            //   //Boolean - whether to make the chart responsive to window resizing
            //   responsive              : true
            // }
        
            // //Create the line chart
            // areaChart.Line(areaChartData, areaChartOptions)
        
            // //-------------
            // //- LINE CHART -
            // //--------------
            // var lineChartCanvas          = $('#lineChart').get(0).getContext('2d')
            // var lineChart                = new Chart(lineChartCanvas)
            // var lineChartOptions         = areaChartOptions
            // lineChartOptions.datasetFill = false
            // lineChart.Line(areaChartData, lineChartOptions)
        
            // //-------------
            // //- PIE CHART -
            // //-------------
            // // Get context with jQuery - using jQuery's .get() method.
            // var pieChartCanvas = $('#pieChart').get(0).getContext('2d')
            // var pieChart       = new Chart(pieChartCanvas)
            // var PieData        = [
            //   {
            //     value    : 700,
            //     color    : '#f56954',
            //     highlight: '#f56954',
            //     label    : 'Chrome'
            //   },
            //   {
            //     value    : 500,
            //     color    : '#00a65a',
            //     highlight: '#00a65a',
            //     label    : 'IE'
            //   },
            //   {
            //     value    : 400,
            //     color    : '#f39c12',
            //     highlight: '#f39c12',
            //     label    : 'FireFox'
            //   },
            //   {
            //     value    : 600,
            //     color    : '#00c0ef',
            //     highlight: '#00c0ef',
            //     label    : 'Safari'
            //   },
            //   {
            //     value    : 300,
            //     color    : '#3c8dbc',
            //     highlight: '#3c8dbc',
            //     label    : 'Opera'
            //   },
            //   {
            //     value    : 100,
            //     color    : '#d2d6de',
            //     highlight: '#d2d6de',
            //     label    : 'Navigator'
            //   }
            // ]
            // var pieOptions     = {
            //   //Boolean - Whether we should show a stroke on each segment
            //   segmentShowStroke    : true,
            //   //String - The colour of each segment stroke
            //   segmentStrokeColor   : '#fff',
            //   //Number - The width of each segment stroke
            //   segmentStrokeWidth   : 2,
            //   //Number - The percentage of the chart that we cut out of the middle
            //   percentageInnerCutout: 50, // This is 0 for Pie charts
            //   //Number - Amount of animation steps
            //   animationSteps       : 100,
            //   //String - Animation easing effect
            //   animationEasing      : 'easeOutBounce',
            //   //Boolean - Whether we animate the rotation of the Doughnut
            //   animateRotate        : true,
            //   //Boolean - Whether we animate scaling the Doughnut from the centre
            //   animateScale         : false,
            //   //Boolean - whether to make the chart responsive to window resizing
            //   responsive           : true,
            //   // Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
            //   maintainAspectRatio  : true,
            //   //String - A legend template
            //   legendTemplate       : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<segments.length; i++){%><li><span style="background-color:<%=segments[i].fillColor%>"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>'
            // }
            // //Create pie or douhnut chart
            // // You can switch between pie and douhnut using the method below.
            // pieChart.Doughnut(PieData, pieOptions)
        
            // //-------------
            // //- BAR CHART -
            // //-------------
            // var barChartCanvas                   = $('#barChart').get(0).getContext('2d')
            // var barChart                         = new Chart(barChartCanvas)
            // var barChartData                     = areaChartData
            // barChartData.datasets[1].fillColor   = '#00a65a'
            // barChartData.datasets[1].strokeColor = '#00a65a'
            // barChartData.datasets[1].pointColor  = '#00a65a'
            // var barChartOptions                  = {
            //   //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
            //   scaleBeginAtZero        : true,
            //   //Boolean - Whether grid lines are shown across the chart
            //   scaleShowGridLines      : true,
            //   //String - Colour of the grid lines
            //   scaleGridLineColor      : 'rgba(0,0,0,.05)',
            //   //Number - Width of the grid lines
            //   scaleGridLineWidth      : 1,
            //   //Boolean - Whether to show horizontal lines (except X axis)
            //   scaleShowHorizontalLines: true,
            //   //Boolean - Whether to show vertical lines (except Y axis)
            //   scaleShowVerticalLines  : true,
            //   //Boolean - If there is a stroke on each bar
            //   barShowStroke           : true,
            //   //Number - Pixel width of the bar stroke
            //   barStrokeWidth          : 2,
            //   //Number - Spacing between each of the X value sets
            //   barValueSpacing         : 5,
            //   //Number - Spacing between data sets within X values
            //   barDatasetSpacing       : 1,
            //   //String - A legend template
            //   legendTemplate          : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
            //   //Boolean - whether to make the chart responsive
            //   responsive              : true,
            //   maintainAspectRatio     : true
            // }
        
            // barChartOptions.datasetFill = false
            // barChart.Bar(barChartData, barChartOptions)
          
      }
    
      handleClose() {
        this.setState({ show: false });
      }
    
      handleShow() {
        this.setState({ show: true });
      }
    
 

    render() {

        

        
        return (
            <div>
                <ContentHeader title='Notifications' small='version 1.0' />

                <Content>
                    <PageHeader title="Box and palettes" />


                    <Row>
                        <Box cols="12" title="New Box Example" icon="tag">
                            <Row>
                                <Grid cols="4 2" >
                                    <Palette color="light-blue" disabled text="Disabled" centered />
                                    <Palette color="light-blue" text="Standart" centered />
                                    <Palette color="light-blue-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="aqua" disabled text="Disabled" centered />
                                    <Palette color="aqua" text="Standart" centered />
                                    <Palette color="aqua-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="green" disabled text="Disabled" centered />
                                    <Palette color="green" text="Standart" centered />
                                    <Palette color="green-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="yellow" disabled text="Disabled" centered />
                                    <Palette color="yellow" text="Standart" centered />
                                    <Palette color="yellow-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="red" disabled text="Disabled" centered />
                                    <Palette color="red" text="Standart" centered />
                                    <Palette color="red-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="gray" disabled text="Disabled" centered />
                                    <Palette color="gray" text="Standart" centered />
                                    <Palette color="gray-active" text="Active" centered />
                                </Grid>
                            </Row>
                            <br />
                            <Row>
                                <Grid cols="4 2" >
                                    <Palette color="navy" disabled text="Disabled" centered />
                                    <Palette color="navy" text="Standart" centered />
                                    <Palette color="navy-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="teal" disabled text="Disabled" centered />
                                    <Palette color="teal" text="Standart" centered />
                                    <Palette color="teal-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="purple" disabled text="Disabled" centered />
                                    <Palette color="purple" text="Standart" centered />
                                    <Palette color="purple-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="orange" disabled text="Disabled" centered />
                                    <Palette color="orange" text="Standart" centered />
                                    <Palette color="orange-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="maroon" disabled text="Disabled" centered />
                                    <Palette color="maroon" text="Standart" centered />
                                    <Palette color="maroon-active" text="Active" centered />
                                </Grid>
                                <Grid cols="4 2" >
                                    <Palette color="black" disabled text="Disabled" centered />
                                    <Palette color="black" text="Standart" centered />
                                    <Palette color="black-active" text="Active" centered />
                                </Grid>
                            </Row>
                        </Box>

                    </Row>
                    <PageHeader title="Alerts" />
                    <Row>
                        <Box cols="12 6" title="Alerts" icon="warning">

                            <DangerAlert title="Alert!" text="Danger alert preview. This alert is dismissable. A wonderful serenity has taken possession of my entire
                                soul, like these sweet mornings of spring which I enjoy with my whole heart."/>
                            <InfoAlert title="Alert!" text="Danger alert preview. This alert is dismissable. A wonderful serenity has taken possession of my entire
                                soul, like these sweet mornings of spring which I enjoy with my whole heart."/>
                            <WarningAlert title="Alert!" text="Danger alert preview. This alert is dismissable. A wonderful serenity has taken possession of my entire
                                soul, like these sweet mornings of spring which I enjoy with my whole heart."/>
                            <SuccessAlert title="Alert!" text="Success alert preview. This alert is dismissable."/>
                        </Box>

                        <Box cols="12 6" title="Modal (bootstrap)" icon="warning" collapsable removable>
                            <div>
                                <p>Click to get the full Modal experience!</p>

                            
                                <Button type="primary" size="lg" name="Launch demo modal" onClick={this.handleShow}/>


                                <Modal show={this.state.show} onHide={this.handleClose}>
                                <Modal.Header>
                                    <Modal.Title><Palette color="light-blue" text="Modal Title" centered /></Modal.Title>
                                </Modal.Header>
                                <Modal.Body>
                                    <h4>Text in a modal</h4>
                                    <hr />
                                    <h4>Overflowing text to show scroll behavior</h4>
                                    <p>
                                    Cras mattis consectetur purus sit amet fermentum. Cras justo odio,
                                    dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta
                                    ac consectetur ac, vestibulum at eros.
                                    </p>
                                </Modal.Body>
                                <Modal.Footer>
                                    <Button type="primary" size="sm" name="Close" onClick={this.handleClose}/>
                                </Modal.Footer>
                                </Modal>
                            </div>
                        </Box>

                        <Box cols="12 6" title="Charts" icon="warning" collapsable removable>
                            <div className="chart">
                                <canvas id="areaChart" style={{height:"250px"}}></canvas>
                            </div>
                        </Box>
                        <Box cols="12 6" title="React-Charts-2" icon="warning" collapsable removable>
                            <div className="chart">
                                <Bar data={this.state.chartData} width={100} height={250} options={{
                                    maintainAspectRatio: false
                                }}
                                />
                            </div>
                        </Box>
                    </Row>
                    <PageHeader title="Progress Bars" />
                    <Row>

                        <Box cols="12 6" title="Horizontal Progress Bars" icon="check">
                        <p>Normal: <code>.standart</code></p>
                        <ProgressBar type="primary" progress="30"/>
                        <p>Stripped Active: <code>.standart</code></p>
                        <ProgressBar  stripped active type="success" progress="70"/>
                        <p>Stripped: <code>.sm</code></p>
                        <ProgressBar size="sm" stripped type="warning" progress="50"/>
                        <p>Active: <code>.xs</code></p>
                        <ProgressBar size="xs" active type="danger" progress="50"/>
                        <p>Normal: <code>.xxs</code></p>
                        <ProgressBar size="xxs" type="primary" progress="85"/>
                        </Box>

                        <Box centered cols="12 6" title="Vertical Progress Bars" icon="check">
                        <p>Same Horizontal plus: <code>.vertical</code></p>
                        <ProgressBar vertical type="primary" progress="30"/>
              
                        <ProgressBar vertical stripped active type="success" progress="70"/>
               
                        <ProgressBar  vertical size="sm" stripped type="warning" progress="50"/>
               
                        <ProgressBar  vertical size="xs" active type="danger" progress="50"/>
              
                        <ProgressBar  vertical size="xxs" type="primary" progress="85"/>
                        </Box>

                    </Row>
                    <PageHeader title="Accordion & Carousel" />
                    <Row>
                    {/* <Box cols="12 6" title="Carousel" icon="warning">
                    <Carousel>
                        <Carousel.Item>
                            <img width={900} height={500} alt="900x500" src="http://www.airtrafficafrica.com/wp-content/uploads/2015/01/900x500.gif" />
                            <Carousel.Caption>
                            <h3>First slide label</h3>
                            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                            </Carousel.Caption>
                        </Carousel.Item>
                        <Carousel.Item>
                            <img width={900} height={500} alt="900x500" src="http://www.airtrafficafrica.com/wp-content/uploads/2015/01/900x500.gif" />
                            <Carousel.Caption>
                            <h3>Second slide label</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </Carousel.Caption>
                        </Carousel.Item>
                        <Carousel.Item>
                            <img width={900} height={500} alt="900x500" src="http://www.airtrafficafrica.com/wp-content/uploads/2015/01/900x500.gif" />
                            <Carousel.Caption>
                            <h3>Third slide label</h3>
                            <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                            </Carousel.Caption>
                        </Carousel.Item>
                        </Carousel>
                        </Box> */}
                 </Row>
                    
                
                   
                    <PageHeader />


                </Content>
            </div >
        )
    }
}