import React, { Component } from 'react'
import ContentHeader from '../../../common/template/content/contentHeader';
import PageHeader from '../../../common/template/content/pageHeader'
import Content from '../../../common/template/content/content'
import SmallBox from '../../../common/widget/smallBox'
import Row from '../../../common//layout/row'
import Box from '../../../common/elements/box'
import Grid from '../../../common//layout/grid'
import Button from '../../../common/elements/button'
import { Line, Doughnut } from 'react-chartjs-2';
import './dashboardBusiness.css'



export default class DashboardBusiness extends Component {
    constructor(props) {
        super(props);
        this.state = {
            themeChange: false,
            isLoading: false,
            donutData: {
                labels: [
                    'Red',
                    'Green',
                    'Yellow'
                ],
                datasets: [{
                    data: [300, 50, 100],
                    backgroundColor: [
                        '#FF6384',
                        '#36A2EB',
                        '#FFCE56'
                    ],
                    hoverBackgroundColor: [
                        '#FF6384',
                        '#36A2EB',
                        '#FFCE56'
                    ]
                }]
            },

            chartData: {
                labels: ['January', 'February', 'March', 'April', 'May', "June", "July"],
                datasets: [
                    {
                        label: 'Income 2017',
                        fill: false,
                        borderCapStyle: 'butt',
                        borderJoinStyle: 'miter',
                        borderDash: [5, 5],
                        borderDashOffset: 0.0,
                        data: [
                            1045,
                            1166,
                            1112,
                            1345,
                            1421,
                            1398,
                            1455
                        ],
                        // borderWidth: 1,
                        // hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
                        // hoverBorderColor: 'rgba(91, 163, 245,1)',

                        // hoverBorderWidth: 2,
                        backgroundColor: 'rgba(70, 130, 180,0.6)',
                        // backgroundColor: [
                        //     'rgba(54, 162, 235, 0.6)',
                        //     'rgba(75, 192, 192, 0.6)',
                        //     'rgba(255, 99, 132, 0.6)',
                        //     'rgba(255, 206, 86, 0.6)',
                        //     'rgba(153, 50, 204, 0.6)',
                        // ],
                        borderColor: 'rgba(70, 130, 180,1)'
                    },
                    {
                        label: 'Income 2018',
                        // fill: false,
                        // borderDash: [5,5],
                        // borderDashOffset: 0.0,
                        //data
                        data: [
                            1145,
                            966,
                            1112,
                            1445,
                            1521,
                            1598,
                            1575
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
                    }

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
                    mode: 'label'
                },
                hover: {
                    mode: 'dataset'
                },
                scales: {
                    xAxes: [
                        {
                            display: true,
                            scaleLabel: {
                                show: true,
                                labelString: 'Month'
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
                                suggestedMin: -10,
                                suggestedMax: 250
                            }
                        }
                    ]

                }

            },
            chartDataLine: {
                labels: ['January', 'February', 'March', 'April', 'May', "June", "July"],
                datasets: [
                    {
                        label: 'Income 2017',
                        // fill: false,

                        // borderDash: [5, 5],
                        borderDashOffset: 0.0,
                        data: [
                            1045,
                            1166,
                            1112,
                            1345,
                            1421,
                            1398,
                            1455
                        ],
                        //Border options
                        borderCapStyle: 'butt',
                        borderJoinStyle: 'miter',
                        borderWidth: 2,
                        borderColor: 'rgba(70, 130, 180,1)',
                        //Point options
                        pointRadius: 3,
                        pointBorderColor: 'rgba(65, 105, 225,1)',
                        pointBackgroundColor: 'rgba(65, 105, 225,1)',
                        //Hover options
                        pointHoverRadius: 5,
                        pointHoverBorderWidth: 2,
                        pointHoverBackgroundColor: 'rgba(91, 163, 245,1)',
                        // borderWidth: 1,
                        // hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
                        // hoverBorderColor: 'rgba(91, 163, 245,1)',

                        // hoverBorderWidth: 2,
                        backgroundColor: 'rgba(70, 130, 180,0.4)',
                        // backgroundColor: [
                        //     'rgba(54, 162, 235, 0.6)',
                        //     'rgba(75, 192, 192, 0.6)',
                        //     'rgba(255, 99, 132, 0.6)',
                        //     'rgba(255, 206, 86, 0.6)',
                        //     'rgba(153, 50, 204, 0.6)',
                        // ],

                    },
                    {
                        label: 'Income 2018',
                        // fill: false,
                        // borderDash: [5,5],
                        // borderDashOffset: 0.0,
                        //data
                        data: [
                            1145,
                            966,
                            1112,
                            1445,
                            1521,
                            1598,
                            1575
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
                        backgroundColor: 'rgba(176, 224, 230,1)',
                        borderColor: 'rgba(135, 206, 235,1)',
                        borderCapStyle: 'butt',
                        borderJoinStyle: 'miter',
                    }

                ]
            },
            optionsLine: {
                maintainAspectRatio: false,
                responsive: true,
                title: {
                    display: true,
                    text: 'Monthly Income'
                },
                tooltips: {
                    mode: 'point',
                    borderWidth: 3,
                    backgroundColor: 'rgba(176, 196, 222,1)',
                    titleFontColor: 'rgba(0,0,0,1)',
                    callbacks: {
                        label: function (tooltipItem, data) {
                            var label = data.datasets[tooltipItem.datasetIndex].label || '';

                            if (label) {
                                label += ': ';
                            }
                            label += Math.round(tooltipItem.yLabel * 100) / 100;
                            return label + ' €';
                        },
                        labelTextColor: function (tooltipItem, chart) {
                            return '#543453';
                        }
                    }


                },
                legend: {
                    display: true,
                    labels: {
                        fontColor: 'rgba(0, 0, 0,1)'
                    }
                },
                hover: {
                    mode: 'dataset'
                },
                scales: {
                    xAxes: [
                        {
                            display: true,
                            scaleLabel: {
                                show: true,
                                labelString: 'Month'
                            }
                        }
                    ],
                    yAxes: [
                        {
                            display: true,
                            scaleLabel: {
                                show: true,
                                labelString: '€'
                            },
                            ticks: {
                                suggestedMin: -10,
                                suggestedMax: 250,
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
                <ContentHeader title='Dashboard' small='Business Summary' />
                <Content>
                    <Row>
                        <SmallBox cols='12 3' color='aqua' icon='bank'
                            value='186' text='Sales per Month' footerText={<span className="lead">-5% <i className={`fa fa-caret-down`} /> </span>} />
                        <SmallBox cols='12 3' color='green' icon='euro'
                            value='1355 €' text='Income per Month' footerText={<span className="lead">+8% <i className={`fa fa-caret-up`} /> </span>} />
                        <SmallBox cols='12 3' color='yellow' icon='user-plus'
                            value='64' text='Customers Registered' footerText={<span className="lead">+12% <i className={`fa fa-caret-up`} /> </span>} />
                        <SmallBox cols='12 3' color='red' icon='calendar-minus-o'
                            value='327 €' text='Expenses Average' footerText={<span className="lead">+0% <i className={`fa fa-caret-right`} /> </span>} />
                    </Row>



                    <Row>
                        <Grid cols="12 8">
                            <Row>
                                <Box cols="12 12" title="INCOME GRAPH" type="primary" icon="line-chart" collapsable>

                                    <Line data={this.state.chartDataLine} options={this.state.optionsLine} width={100} height={350} />

                                </Box>

                            </Row>
                            <Row>
                                <Box cols="12 12" title="LINE CHART" type="primary" collapsable>

                                    <Line data={this.state.chartData} options={this.state.options} width={100} height={300} />

                                </Box>

                            </Row>

                        </Grid>
                        <Grid cols="12 4">
                            <Row>
                                
                                    <div className="donut_graph">
                                        <h2 className="donut_title">Top Sales</h2>
                                        <Doughnut data={this.state.donutData} width={100} height={50} />
                                    </div>

                            </Row>
                            <Row>
                            <div className="donut_graph">
                                        <h2 className="donut_title">Top Expenses</h2>
                                        <Doughnut data={this.state.donutData} width={100} height={50} />
                                    </div>
                            </Row>
                        </Grid>

                    </Row>






                    <PageHeader />


                </Content>
            </div>
        )
    }
}