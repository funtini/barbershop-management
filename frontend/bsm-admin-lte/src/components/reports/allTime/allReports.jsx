import React, { Component } from 'react'

//elements
import ContentHeader from '../../../common/template/content/contentHeader';
import Content from '../../../common/template/content/content'
import Grid from '../../../common//layout/grid'
import InfoBox from '../../../common/widget/infoBox'
import Row from '../../../common//layout/row'
import Box from '../../../common/elements/box'
import BoxCard from '../../../common/elements/boxCard'

//ui
import Button from '../../../common/UI/button'

//components
import ReportsTable from "../reportsTable/reportsTable"

//libs
import { HorizontalBar } from 'react-chartjs-2';

export default class AllReports extends Component {
    constructor(props) {
        super(props);

        this.state = {
            chartData: {
                labels: ['Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta','Sábado'],
                datasets: [
                    {
                        label: 'Income',
                        borderCapStyle: 'butt',
                        borderJoinStyle: 'miter',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        data: [
                            251,
                            181,
                            211,
                            41,
                            98,
                            150
                        ],
                        borderWidth: 1,
                        hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
                        hoverBorderColor: 'rgba(91, 163, 245,1)',

                        hoverBorderWidth: 2,
                        backgroundColor: [
                            'rgba(54, 162, 235, 0.6)',
                            'rgba(75, 192, 192, 0.6)',
                            'rgba(255, 99, 132, 0.6)',
                            'rgba(255, 206, 86, 0.6)',
                            'rgba(153, 50, 204, 0.6)',
                        ],
                        borderColor: 'rgba(75,192,192,1)',
                        
                    }
                ]
            }
        }
    };

    renderTopBoxes() {
        return (
            <Grid cols="12 12">
                <br />
                <Row>
                    <InfoBox cols='12 3' color='green' icon='users'
                        value='87' text='Profit' progress='5' />
                    <InfoBox cols='12 3' color='blue' icon='user'
                        value='5' text='Income' progress='5' />
                    <InfoBox cols='12 3' color='red' icon='user'
                        value='22,5' text='Expenses' progress='0' />

                    <InfoBox cols='12 3' color='white' icon='user'
                        value='Pedro Domingos' text='Employer of The Month' progress='100' />
                </Row>
            </Grid>
        )
    }

    


    render() {

        return (
            <div>
                <ContentHeader title='Reports' small='Summary' />
                <Content><div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} />
                    <Row>
                        {this.renderTopBoxes()}
                    </Row>
                    <Row>
                    <Box cols="12 6" title="REPORTS" type="primary" collapsable >
                                    <ReportsTable cols="12 12" />
                                </Box>
                                {/* <Box cols="12 3" title="WEEK DAY INCOME" type="primary" collapsable>
                                    <div className="chart">
                                        <HorizontalBar data={this.state.chartData} width={100} height={250} options={{
                                            maintainAspectRatio: false}}
                                        />
                                    </div>
                                </Box> */}
                                <BoxCard cols="12 4" title="WEEK DAY INCOME">
                                    <div className="chart">
                                        <HorizontalBar data={this.state.chartData} width={100} height={250} options={{
                                            maintainAspectRatio: false}}
                                        />
                                    </div>
                                </BoxCard>
                                <BoxCard cols="12 4" title="buttons">
                                    <Button type="primary">
                                    ola
                                    </Button>
                                </BoxCard>
                            </Row>
                            <Row>
                                {/* <Box cols="12 6" title="REPORTS" type="primary" collapsable >
                                    <ReportsTable cols="12 12" />
                                </Box> */}
                            </Row>

                </Content>
            </div>
        )
    }
}