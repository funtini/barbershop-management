import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import SmallBox from '../../common/widget/smallBox'
import Row from '../../common//layout/row'
import Box from '../../common/elements/box'
import Grid from '../../common//layout/grid'
import Button from '../../common/elements/button'
import Calendar from '../../common/eventCalendar/calendar'
import { Line } from 'react-chartjs-2';



export default class Dashboard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            themeChange: false,
            isLoading: false,
            chartData: {
                labels: ['1', '2', '3', '6', '7', "8", "9",'10', '13', '14', '15'],
                datasets: [
                    
                    {
                        label: 'Income',
                        // fill: false,
                        // borderDash: [5,5],
                        // borderDashOffset: 0.0,
                        //data
                        data: [
                            55,
                            70,
                            68,
                            65,
                            58,
                            70,
                            60,
                            50,
                            74
                        ],
                        //Line Style
                        tension: 0.4,
                        //Point options
                        pointRadius: 5,
                        pointBorderColor: 'rgba(255, 250, 240,1)',
                        pointBackgroundColor: 'rgba(91, 163, 245,1)',
                        //Hover options
                        pointHoverRadius: 7,
                        pointHoverBorderWidth: 2,
                        pointHoverBackgroundColor: 'rgba(91, 163, 245,1)',
                        // hoverBorderColor: 'rgba(91, 163, 245,1)',
                        // hoverBorderWidth: 2,
                        //Border options
                        borderWidth: 2,
                        backgroundColor: 'rgba(176, 224, 230,0.6)',
                        borderColor: 'rgba(135, 206, 235,1)',
                        borderCapStyle: 'butt',
                        borderJoinStyle: 'miter',
                    },
                    {
                        label: 'Daily Average',
                        fill: false,
                        // borderCapStyle: 'butt',
                        // borderJoinStyle: 'miter',
                         //Line Style
                         tension: 0,
                         pointRadius: 0,
                         borderWidth: 2,
                        // borderDash: [5, 5],
                        // borderDashOffset: 0.0,
                        data: [
                            65,
                            65,
                            65,
                            65,
                            65,
                            65,
                            65,
                            65,
                            65,
                            65,
                            65
                        ],
              
                        backgroundColor: 'rgba(255, 215, 0,0.4)',
           
                        borderColor: 'rgba(218, 165, 32,1)'
                    },

                ]
            },
            options: {
                maintainAspectRatio: false,
                responsive: true,
                title: {
                    display: true,
                    text: 'Income Evolution'
                },
                tooltips: {
                    mode: 'label',
                    // borderWidth: 3,
                    // backgroundColor: 'rgba(176, 196, 222,1)',
                    // titleFontColor: 'rgba(0,0,0,1)',
                    callbacks: {
                        label: function (tooltipItem, data) {
                            var label = data.datasets[tooltipItem.datasetIndex].label || '';

                            if (label) {
                                label += ': ';
                            }
                            label += Math.round(tooltipItem.yLabel * 100) / 100;
                            return label + ' €';
                        },
                        // labelTextColor: function (tooltipItem, chart) {
                        //     return '#543453';
                        // }
                    }


                }
               ,
                hover: {
                    mode: 'dataset'
                },
                scales: {
                    xAxes: [
                        {
                            display: true,
                            scaleLabel: {
                                show: true,
                                labelString: 'Day'
                            }
                        }
                    ],
                    yAxes: [
                        {
                            display: true,
                            scaleLabel: {
                                show: true,
                                labelString: 'Value'
                            },
                            ticks: {
                                suggestedMin: 0,
                                suggestedMax: 100,
                                // Include a dollar sign in the ticks
                                callback: function (value, index, values) {
                                    return value + ' €';
                                }
                            }
                        }
                    ]

                }

            }
        }
          
    }


    render() {
        
        return (
            <div> 
                <ContentHeader title='Dashboard' small='Today Summary'/>
                <Content>
                     <Row>
                        <SmallBox cols='12 3' color='aqua' icon='bank'
                            value='6' text='Sales' footerTitle="Average:" footerText="8" />
                        <SmallBox cols='12 3' color='green' icon='euro'
                            value='55 €' text='Today Income' footerTitle="Average Diff:" footerText="-12%"/>
                        <SmallBox cols='12 3' color='yellow' icon='line-chart'
                            value='65 €' text='Daily Average' footerTitle="Growth last 30 days:" footerText="+5%" />
                        <SmallBox cols='12 3' color='red' icon='calendar-minus-o'
                            value='12' text='Bookings' footerTitle="Next Booking:" footerText="14h30" progress="50" />            
                    </Row>   
                    <Row>
                    <Grid cols="12 6">
                    <Row>
                    <Box cols="12 12" icon="edit" title="QUICK ACTIONS" type="info" collapsable solid>
                        <Row>
                            <Grid cols="12 4">
                            <div className="text-center">
                                <Button icon="plus-square-o fa-lg" type="primary" size="xl" style={{ fontSize: '4em', width: '2.5em' }} />
                                <p className="text-light-blue" style={{ fontSize: '1em',fontFamily:'Georgia, serif'}}>NEW SALE</p>
                                </div>
                            </Grid>
                            <Grid cols="12 4">
                            <div className="text-center">
                                <Button icon="calendar-plus-o fa-lg" type="primary" size="xl" style={{ fontSize: '4em', width: '2.5em' }} />
                                <p className="text-light-blue" style={{ fontSize: '1em', fontFamily:'Georgia, serif' }}>NEW BOOKING</p>
                                </div>
                            </Grid>
                            <Grid cols="12 4">
                            <div className="text-center">
                                <Button icon="user-plus fa-lg" type="primary" size="xl" style={{ fontSize: '4em', width: '2.5em' }} />
                                <p className="text-light-blue" style={{ fontSize: '1em',fontFamily:'Georgia, serif'}}>NEW CUSTOMER</p>
                                </div>
                            </Grid>
                            </Row>
                        </Box>
                       
                    </Row>  
                    <Row>
                        <Box cols="12 12" title="DAILY INCOME - SEPTEMBER" type="primary" collapsable>

                            <Line data={this.state.chartData} options={this.state.options} width={100} height={300} />

                        </Box>

                    </Row>
                    </Grid>
                    <Grid cols="12 6">
                    <Row>
                    <Box cols="12 12"type="info" noPadding noHeader >
                    <Calendar height="auto" slotDuration='00:30:00' defaultView='agendaDay'/>

                    </Box>
                    </Row> 
                    </Grid>
                    </Row> 


                    


                    <Row>
                        
                    </Row>
                     
              
                    <Row>
                        
                    </Row>
     


                    <PageHeader/> 
              
                    
                </Content>
            </div>
        )
    }
}