import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import ValueBox from '../../common/widget/valueBox'
import SmallBox from '../../common/widget/smallBox'
import InfoBox from '../../common/widget/infoBox'
import SimpleBox from '../../common/widget/simpleBox'
import Row from '../../common//layout/row'
import Grid from '../../common//layout/grid'
import UserInfo from '../../common/widget/userInfo';
import Box from '../../common/elements/box'
import InputBox from '../../common/elements/inputBox'
import Label from '../../common/elements/label'
import Button from '../../common/elements/button'
import DataTable from '../../common/tables/dataTable'
import { Bar, Pie } from 'react-chartjs-2';
import ProductTable from './productTable';
import moment from 'moment';

export default class Products extends Component {
    constructor(props) {
        super(props);
        this.state = {
            themeChange: false,
            isLoading: false,
            chartData: {
                labels: ['Corte Simples', 'Corte Completo', 'Barba Curta', 'Barba Longa', 'All Extra'],
                datasets: [
                    {
                        label: 'Products',
                        borderCapStyle: 'butt',
                        borderJoinStyle: 'miter',
                        borderDash: [],
                        borderDashOffset: 0.0,
                        data: [
                            251,
                            181,
                            211,
                            41,
                            98
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
                        borderColor: 'rgba(75,192,192,1)'
                    }
                ]
            }
        };
        this.inputName = React.createRef();
        this.inputType = React.createRef();
        this.inputPrice = React.createRef();
        this.onChange = this.onChange.bind(this);
        this.clearFields = this.clearFields.bind(this);
        this.handleAddClick = this.handleAddClick.bind(this);
        this.handleRemoveClick = this.handleRemoveClick.bind(this);
        // this.handleChangeTheme = this.handleChangeTheme.bind(this);
    }


    onChange = (e) => {
        console.log("Select changed to..")
        console.log(e.target.value)
    }

    clearFields() {
        this.inputName.current.value = "";
        this.inputType.current.value = "Haircut";
        this.inputPrice.current.value = "";
    }

    handleAddClick() {
        console.log('form values: ' +
            this.inputName.current.value + ', ' +
            this.inputType.current.value + ', ' +
            this.inputPrice.current.value + '€'
        )
        this.clearFields();

    }

    handleRemoveClick() {
        console.log("funciona")

    }

    // handleChangeTheme(){
    //     this.setState({themeChange:!this.state.themeChange})

    // }

    render() {
        var current_time = new moment().format("MMMM, YYYY");
        return (
            <div>
                <ContentHeader title='Products' small='Summary' />

                <Content><div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} />
                    <Row>
                        <Grid cols="12 6">
                            <br />{this.state.themeChange ?
                                <Row>
                                    <SmallBox cols='12 6' color='blue' icon='bank'
                                        value='87' text='Total Customers' footer="Last Month: 83 (+5%)" />
                                    <SmallBox cols='12 6' color='blue' icon='credit-card'
                                        value='5' text='Added this month' footer="Average: 5 (+0%)" />
                                </Row> : <Row>

                                    <InfoBox cols='12 6' color='green' icon='archive'
                                        value='8' text='Total Products' progress='5' />
                                    <InfoBox cols='12 6' color='aqua-active' icon='cut'
                                        value='Corte Simples' text='Best Seller' progress='55' footer='% Sales in 30 days' />
                                </Row>
                            }
                            {/* <Switch checked={this.state.themeChange} label="Change Theme" onChange={this.handleChangeTheme}/> */}
                            <div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} /><br />
                            <Row>
                                <Box cols="12 12" title="NEW PRODUCT" icon="edit" type="primary" collapsable solid>
                                    <Row>
                                        <div className="form-group" style={{ paddingLeft: '1em' }}>
                                            <Grid cols="12 5">
                                                <label>Name</label>
                                                <input ref={this.inputName} className={`form-control`} type={`text`} placeholder={`Enter Product's Name`} style={{ width: `100%` }} />
                                            </Grid>
                                            <Grid cols="12 3">
                                                <label>Type</label>
                                                <select ref={this.inputType} onChange={this.onChange} className="form-control select2" style={{ width: `100%` }}>
                                                    <option defaultChecked>Haircut</option>
                                                    <option>Shave</option>
                                                    <option>Extra</option>
                                                </select>
                                            </Grid>
                                            <Grid cols="12 3">
                                                <label>Price (€)</label>
                                                <input ref={this.inputPrice} className={`form-control`} type={`number`} placeholder={`Enter Price`} style={{ width: `100%` }} />
                                            </Grid>
                                        </div>
                                    </Row>
                                    <br />
                                    <div className="box-footer">
                                        <Button type="default" onClick={this.clearFields} name="Clear Fields" />
                                        <Button type="primary" onClick={this.handleAddClick} name="ADD PRODUCT" pullright />
                                    </div>
                                </Box>
                            </Row>
                            <Row>
                                <Box cols="12 12" title="LIST OF PRODUCTS" type="primary" collapsable >
                                    <ProductTable cols="12 12" striped />
                                </Box>
                            </Row>
                            <Row>
                                <Box cols="12 12" title="PRODUCTS GRAPH" type="primary" collapsable>
                                    <div className="chart">
                                        <Bar data={this.state.chartData} width={100} height={250} options={{
                                            maintainAspectRatio: false
                                        }}
                                        />
                                    </div>
                                </Box>
                            </Row>
                        </Grid>
                        <Grid cols="12 6">
                            <br />
                            <Row>
                                <Box cols="12 12" title="PRODUCTS SALES [%]" type="primary" collapsable>
                                    <div className="chart">
                                        <Pie data={this.state.chartData} options={{
                                            title: {
                                                display: true,
                                                text: current_time,
                                                fontSize: 20
                                            },
                                            legend: {
                                                display: true,
                                                position: 'right'
                                            }
                                        }}
                                        />
                                    </div>
                                </Box>
                            </Row>
                            <Row>
                                {/* <PageHeader title="Additional Information"/>  */}
                                <Box cols="12 6" title="STATISTICS" type="primary" collapsable >
                                    <h5 >h4. Bootstrap heading</h5>
                                    <p className="text-light-blue">Text light blue to emphasize info (2)</p>
                                </Box>
                                <Box cols="12 6" title="STATISTICS" type="primary" collapsable >
                                    <h5 >h4. Bootstrap heading</h5>
                                    <p className="text-light-blue">Text light blue to emphasize info (2)</p>
                                </Box>
                                <Box cols="12 6" title="STATISTICS" type="primary" collapsable >
                                    <h5 >h4. Bootstrap heading</h5>
                                    <p className="text-light-blue">Text light blue to emphasize info (2)</p>
                                </Box>
                                <Box cols="12 6" title="STATISTICS" type="primary" collapsable >
                                    <h5 >h4. Bootstrap heading</h5>
                                    <p className="text-light-blue">Text light blue to emphasize info (2)</p>
                                </Box>
                            </Row>
                        </Grid>
                    </Row>
                    <PageHeader />
                </Content>
            </div>
        )
    }
}