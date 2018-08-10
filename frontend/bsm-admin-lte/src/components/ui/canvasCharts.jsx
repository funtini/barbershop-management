import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import PageHeader from '../../common/template/content/pageHeader'
import Row from '../../common/layout/row'
import Box from '../../common/elements/box'
import Chart from 'chart.js';
import CanvasJSReact from '../../common/charts/canvasjs.react';
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

export default class CanvasCharts extends Component {


render() {

    const options = {
        title: {
          text: "Basic Column Chart in React"
        },
        data: [{				
                  type: "column",
                  dataPoints: [
                      { label: "Apple",  y: 10  },
                      { label: "Orange", y: 15  },
                      { label: "Banana", y: 25  },
                      { label: "Mango",  y: 30  },
                      { label: "Grape",  y: 28  }
                  ]
         }]
        }

        
        return (
            <div>
                <ContentHeader title='Charts' small='version 1.0' />

                <Content>
               


                    <Row>
                        <Box cols="12 4" title="My Chart" icon="edit" primary >
                        <div>
        <CanvasJSChart options = {options}
            /* onRef = {ref => this.chart = ref} */
        />
      </div>
                        </Box>
                        <Box cols="12 4" title="Area/Bar Chart" icon="edit" primary >
   
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