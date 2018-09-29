import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import SmallBox from '../../common/widget/smallBox'
import InfoBox from '../../common/widget/infoBox'
import Row from '../../common//layout/row'
import Grid from '../../common//layout/grid'
import Box from '../../common/elements/box'
import Button from '../../common/elements/button'
import { Bar, Pie } from 'react-chartjs-2';
import ProductTable from './productTable';
import BoxHeader from '../../common/elements/boxHeader'
import moment from 'moment';

export default class Products extends Component {
    constructor(props) {
        super(props);
        this.state = {
            themeChange: false,
            isLoading: false,
            barChartData: {
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
                        backgroundColor:
                            'rgba(54, 162, 235, 0.6)',

                        borderColor: 'rgba(75,192,192,1)'
                    }
                ]
            },
            pieData: {
                labels: ['Corte Simples', 'Corte Completo', 'Barba Curta', 'Barba Longa', 'All Extra'],
                datasets: [
                    {
                        data: [
                            251,
                            181,
                            211,
                            41,
                            98
                        ],
                        // borderWidth: 1,
                        hoverBackgroundColor: [
                            'rgba(54, 162, 235, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(255, 99, 132, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(153, 50, 204, 1)',
                        ],
                        // hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
                        // hoverBorderColor: 'rgba(91, 163, 245,1)',

                        hoverBorderWidth: 5,
                        backgroundColor: [
                            'rgba(54, 162, 235, 0.6)',
                            'rgba(75, 192, 192, 0.6)',
                            'rgba(255, 99, 132, 0.6)',
                            'rgba(255, 206, 86, 0.6)',
                            'rgba(153, 50, 204, 0.6)',
                        ],
                        // borderColor: 'rgba(75,192,192,1)'
                    }
                ]

            },
            pieOptions: {
                // title: {
                //     display: true,
                //     text: current_time,
                //     fontSize: 20
                // },
                legend: {
                    display: true,
                    position: 'right'
                },
                maintainAspectRatio: false
                //dognhut 
                // cutoutPercentage:50
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

                                    <Bar data={this.state.barChartData} width={100} height={250} options={{
                                        maintainAspectRatio: false
                                    }}
                                    />

                                </Box>
                            </Row>
                        </Grid>
                        <Grid cols="12 6">
                            <br />
                            <Row>
                                <Box cols="12 7" title="PRODUCTS SALES [%]" type="primary" collapsable>

                                    <Pie data={this.state.pieData} options={this.state.pieOptions} width={100} height={260}/>

                                </Box>
                                <Box cols="12 5" title="STATISTICS" type="warning" collapsable >
                                <BoxHeader title="Best Extra Product" small="[ Total Sales ]"/>
                                    <Grid cols="12 6">
                                    <p className="text-light-blue" style={{fontSize:17}}>Gel Cabelo</p>
                                    <p className="text-purple">Amaciador Barba</p>
                                    <p className="text-purple" style={{fontSize:13}}>Produto Cabelo</p>
                                    <p className="text-purple" style={{fontSize:12}}>Produto Xpto</p>
                                    <p className="text-purple" style={{fontSize:12}}>Produto Qwerty</p>
                                    </Grid>
                                    <Grid cols="12 6">
                                    <p className="text-light-blue pull-right">12</p>
                                    <Row/>
                                    <p className="text-purple pull-right">10</p>
                                    <Row/>
                                    <p className="text-purple pull-right">7</p>
                                    <Row/>
                                    <p className="text-purple pull-right">5</p>
                                    <Row/>
                                    <p className="text-purple pull-right">2</p>
                                    </Grid>
                                </Box>
                            </Row>
                            
                            <Row>
                            
                                <Box cols="12 12" title="CURRENT MONTH SOLD PRODUCTS" type="primary" collapsable >
                                <Bar data={this.state.barChartData} width={100} height={250} options={{
                                        maintainAspectRatio: false
                                    }}
                                    />
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