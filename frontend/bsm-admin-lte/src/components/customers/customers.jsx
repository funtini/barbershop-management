import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import BoxHeader from '../../common/elements/boxHeader'
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
import './customers.css'
import { Bar } from 'react-chartjs-2';
import CustomersTable from './customersTable';
// import { Switch } from "@blueprintjs/core";


export default class Customers extends Component {
  constructor(props) {
    super(props);
    this.state = {
      themeChange: false,
      isLoading: false,
      chartData: {
        labels: ['Simple Haircut', 'Full Haircut', 'Shave', 'HairGel', 'CleanShave', 'Shave&Haircut'],
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
              30,
              41,
              98
            ],
            borderWidth: 1,
            hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
            hoverBorderColor: 'rgba(91, 163, 245,1)',

            hoverBorderWidth: 2,
            backgroundColor: 'rgba(91, 163, 245,1)',
            borderColor: 'rgba(75,192,192,1)'
          }
        ]
      }
    };


    // this.handleChangeTheme = this.handleChangeTheme.bind(this);
  }




  // handleChangeTheme(){
  //     this.setState({themeChange:!this.state.themeChange})

  // }

  render() {
    var dataHeader = ["Um", "Dois", "Tres", "Quatro", "Quinta"]

    return (
      <div>
        <ContentHeader title='Customers' small='Summary' />

        <Content><div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} /><br />{this.state.themeChange ?

          <Row>

            <SmallBox cols='12 3' color='blue' icon='users'
              value='87' text='Total Customers' footer="Last Month: 83 (+5%)" />
            <SmallBox cols='12 3' color='blue' icon='credit-card'
              value='5' text='Added this month' footer="Average: 5 (+0%)" />
            <SmallBox cols='12 3' color='blue' icon='money'
              value='22,5' text='Average Age' footer="Last Month: 23 (-0.5%)" />
            <SmallBox cols='12 3' color='green' icon='money'
              value='Mangualde' text='Average Location' footer="Number of Customers: 55" />
          </Row> : <Row>

            <InfoBox cols='12 3' color='green' icon='users'
              value='87' text='Total Customers' progress='5' />
            <InfoBox cols='12 3' color='aqua-active' icon='user'
              value='5' text='Added this month' progress='5' />
            <InfoBox cols='12 3' color='aqua-active' icon='user'
              value='22,5' text='Average Age' progress='0' />

            <InfoBox cols='12 3' color='aqua-active' icon='user'
              value='Mangualde' text='Average Location' progress='20' />


          </Row>
        }

          {/* <Switch checked={this.state.themeChange} label="Change Theme" onChange={this.handleChangeTheme}/> */}
          <div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} /><br />
          {/* <PageHeader /> */}

          <Row>
          <Grid cols="12 9">
          <Row>
            <Box cols="12 8" title="NEW CUSTOMER" icon="user-plus" type="primary" collapsable solid>
              <InputBox type="text" label="Name" placeholder="Enter Name" cols="6" />
              <InputBox type="text" label="Phone" placeholder="Enter Phone Number" width="40" />
              <InputBox type="text" label="Birth Date" placeholder="YYYY/MM/DD" cols="6" />
              <InputBox type="text" label="Address" placeholder="Enter Location Name" width="40" />
              <div className="box-footer">
                <Button type="primary" name="ADD CUSTOMER" className="btn-add-customer" />
                <Button name="Clear Fields" className="btn-add-customer" pullright />
              </div>
            </Box>
            <Box cols="12 4" title="RECENTLY ADDED" icon="user" type="primary" collapsable>
              <ul className="products-list product-list-in-box">
                <li className="item">
                  <div className="product-img">
                    <img src="https://mira-mama.com/themes/dor_organick3/assets/img/dorado/avatar_user.jpg" alt="Product Image" />
                  </div>
                  <div className="product-info">
                    <div className="product-title text-blue">António Rodrigues
              <span className="label label-success pull-right">5h22m</span></div>
                    <span className="product-description">
                      Mangualde
            </span>
                  </div>
                </li>
                {/* /.item */}
                <li className="item">
                  <div className="product-img">
                    <img src="https://mira-mama.com/themes/dor_organick3/assets/img/dorado/avatar_user.jpg" alt="Product Image" />
                  </div>
                  <div className="product-info">
                    <div className="product-title text-blue">Ruben Sarmento
              <span className="label label-success pull-right">1d</span></div>
                    <span className="product-description">
                      Mangualde
            </span>
                  </div>
                </li>
                
                {/* /.item */}
                <li className="item">
                  <div className="product-img">
                    <img src="https://mira-mama.com/themes/dor_organick3/assets/img/dorado/avatar_user.jpg" alt="Product Image" />
                  </div>
                  <div className="product-info">
                    <div className="product-title text-blue">Vitor Dias
              <span className="label label-success pull-right">2d</span></div>
                    <span className="product-description">
                      Nelas
            </span>
                  </div>
                </li>
                {/* /.item */}
              </ul>
            </Box>
</Row>
<Row>

            <Box cols="12 12" icon="edit" title="LIST OF CUSTOMERS" type="primary" collapsable>
                <CustomersTable bordered striped/>

              {/* <DataTable cols='12' column={dataHeader} /> */}

            </Box>
</Row>


            </Grid>



            <Grid cols="12 3">
            <Row>
            <Box cols="12 12" title="STATISTICS - 2018" type="primary" icon="tag" collapsable>
          
            <BoxHeader title="Best Customer" small="[ Total Income ]"/>
            <Grid cols="12 6">
            <p className="text-light-blue" style={{fontSize:17}}>João Dias</p>
            <p className="text-purple">Rogerio Matias</p>
            <p className="text-purple" style={{fontSize:13}}>Pedro Nuno</p>
            </Grid>
            <Grid cols="12 6">
            <p className="text-light-blue pull-right">104 €</p>
            <Row/>
            <p className="text-purple pull-right">94 €</p>
            <Row/>
            <p className="text-purple pull-right">88 €</p>
            </Grid>
            

            <Row/>
            <BoxHeader title="Show Up Longest Period" small="[ Last Sale ]" smallTwo="[ Sales ]"/>
            <Grid cols="12 6">
            <p className="text-light-blue" style={{fontSize:17}}>Pedro Pereira</p>
            <p className="text-purple">Filipe Sousa</p>
            <p className="text-purple" style={{fontSize:13}}>Ruben Filipe</p>
            </Grid>
            <Grid cols="12 6">
            <p className="text-light-blue pull-right">226 dias</p>
            <Row/>
            <p className="text-purple pull-right">191 dias</p>
            <Row/>
            <p className="text-purple pull-right">164 dias</p>
            </Grid>


            </Box>
            </Row>
            <Row>
            
            <Box cols="12 12" title="STATISTICS - ALL TIME" icon="tag" type="primary" collapsable>
           
            <BoxHeader title="Best Customer" small="[ Total Income ]"/>
            <Grid cols="12 6">
            <p className="text-light-blue" style={{fontSize:17}}>João Dias</p>
            <p className="text-purple">Rogerio Matias</p>
            <p className="text-purple" style={{fontSize:13}}>Vitor Bruno</p>
            </Grid>
            <Grid cols="12 6">
            <p className="text-light-blue pull-right">324 €</p>
            <Row/>
            <p className="text-purple pull-right">294 €</p>
            <Row/>
            <p className="text-purple pull-right">274 €</p>
            </Grid>
            <Row/>
            <BoxHeader title="Show Up Longest Period" small="[ Last Sale ]"/>
            <Grid cols="12 6">
            <p className="text-light-blue" style={{fontSize:17}}>Pedro Almeida</p>
            <p className="text-purple">Sandro Almeida</p>
            <p className="text-purple" style={{fontSize:13}}>Pedro Pereira</p>
            </Grid>
            <Grid cols="12 6">
            <p className="text-light-blue pull-right">526 dias</p>
            <Row/>
            <p className="text-purple pull-right">491 dias</p>
            <Row/>
            <p className="text-purple pull-right">326 dias</p>
            </Grid>

            </Box>
          </Row>
            </Grid>

          </Row>
          {/* <PageHeader title="Additional Information"/>  */}
         
          <Row>
            <Box cols="12 6" title="CUSTOMERS GRAPH" icon="bar-chart" type="primary" collapsable>
              <div className="chart">
                <Bar data={this.state.chartData} width={100} height={250} options={{
                  maintainAspectRatio: false
                }}
                />
              </div>


            </Box>
          </Row>



          <PageHeader />


        </Content>
      </div>
    )
  }
}