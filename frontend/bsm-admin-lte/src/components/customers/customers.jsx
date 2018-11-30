import React, { Component } from 'react'

//elements
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import PageHeader from '../../common/template/content/pageHeader';
import Row from '../../common//layout/row';
import Grid from '../../common//layout/grid';
import Box from '../../common/elements/box';
import InfoBox from '../../common/elements/infoBox';
import BoxHeader from '../../common/elements/boxHeader'
import BoxCard from '../../common/elements/boxCard'
import SmallBox from '../../common/widget/smallBox'

import InputBox from '../../common/elements/inputBox'
import Button from '../../common/elements/button'

//components
import CustomersTable from './customersTable';

//libs
import { Bar, Line, Pie, HorizontalBar } from 'react-chartjs-2';

//style
import './customers.css'

import SimpleBox from '../../common/widget/simpleBox'
import ValueBox from '../../common/widget/valueBox'
import UserInfo from '../../common/widget/userInfo';
import Label from '../../common/elements/label'
import DataTable from '../../common/tables/dataTable'

export default class Customers extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: false,
      chartData: {
        labels: ['Mangualde', 'Nelas', 'Penalva', 'Mesquitela', 'Canas'],
        datasets: [
          {
            label: 'Number of Customers',
            borderCapStyle: 'butt',
            borderJoinStyle: 'miter',
            borderDash: [],
            borderDashOffset: 0.0,
            data: [
              94,
              34,
              27,
              16,
              13
            ],
            borderWidth: 1,
            hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
            hoverBorderColor: 'rgba(91, 163, 245,1)',

            hoverBorderWidth: 2,
            backgroundColor: 'rgba(91, 163, 245,1)',
            borderColor: 'rgba(75,192,192,1)'
          }
        ]
      },
      regData: {
        labels: ['JAN', 'FEV', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AGO', 'SET', 'OUT', 'NOV', 'DEC'],
        datasets: [
          {
            label: 'New Registrations',
            data: [
              5,
              2,
              3,
              1,
              0,
              3,
              2,
              5,
              // 8,
              // 3,
              // 1,
              // 1,
            ],
            borderWidth: 1,
              labelFontSize: 1,
            hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
            hoverBorderColor: 'rgba(91, 163, 245,1)',
            // hoverBackgroundColor: 'rgba(91, 163, 245,0.4)',
            // hoverBorderColor: 'rgba(91, 163, 245,1)',
            //Point options
            pointRadius: 5,
            pointBorderColor: 'rgba(255, 250, 240,1)',
            pointBackgroundColor: 'rgba(91, 163, 245,1)',
            //Hover options
            pointHoverRadius: 7,
            pointHoverBorderWidth: 2,
            pointHoverBackgroundColor: 'rgba(91, 163, 245,1)',
            hoverBorderWidth: 2,
            backgroundColor: 'rgba(91, 163, 245,0.6)',
            borderColor: 'rgba(75,192,192,1)'
            // borderColor: 'rgba(75,192,192,1)'
          }
        ]

      }
      ,
      regOptions: {
        responsive: true,
          scales: {
              xAxes: [{
                  ticks: {
                      fontSize: 9
                  }
              }]
          }
      ,
        // title: {
        //     display: true,
        //     text: 'New Registrations 2018'
        // },
        maintainAspectRatio: false
      }
    };


    // this.handleChangeTheme = this.handleChangeTheme.bind(this);
  }




  // handleChangeTheme(){
  //     this.setState({themeChange:!this.state.themeChange})

  // }

  render() {

    return (
      <div>
        <ContentHeader title='Customers' small='Summary' />
        <Content>
          <div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} />
          <br />

          <Row>
            <InfoBox cols='12 3' color='green' icon='users'
              value='87' text='Total Customers' progress='5' />
            <InfoBox cols='12 3' color='aqua-active' icon='user'
              value='5' text='Added this month' progress='50' />
            <InfoBox cols='12 3' color='aqua-active' icon='user'
              value='22,5' text='Average Age' footer='At least with 3 sales' />
            <InfoBox cols='12 3' color='aqua-active' icon='user'
              value='Mangualde' text='Average Location' footer='52 customers' />
          </Row>

          <div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} /><br />

          <Row>
            <Grid cols="12 12 7">
              <Row>

                  <Box cols="12 12" icon="edit" title="LIST OF CUSTOMERS" type="primary" collapsable style={{minWidth:'fit-content'}}>
                      <CustomersTable bordered striped />

                      {/* <DataTable cols='12' column={dataHeader} /> */}

                  </Box>

                      <Box cols="12 12" title="REGISTRATION STATS - [ 2018 ]" icon="bar-chart" type="primary" collapsable>
                          <Line data={this.state.regData} width={200} height={210} options={this.state.regOptions} />
                      </Box>

          


                {/* <Box cols="12 6" title="NEW CUSTOMER" icon="user-plus" type="primary" collapsable solid>
              <InputBox type="text" label="Name" placeholder="Enter Name" cols="6" />
              <InputBox type="text" label="Phone" placeholder="Enter Phone Number" width="40" />
              <InputBox type="text" label="Birth Date" placeholder="YYYY/MM/DD" cols="6" />
              <InputBox type="text" label="Address" placeholder="Enter Location Name" width="40" />
              <div className="box-footer">
                <Button type="primary" name="ADD CUSTOMER" className="btn-add-customer" />
                <Button name="Clear Fields" className="btn-add-customer" pullright />
              </div>
            </Box> */}


              </Row>
            </Grid>
            <Grid cols="12 12 5">
              <Row>
                <Box cols="12 12" title="LOCATION STATS" icon="bar-chart" type="primary" collapsable>
                  <HorizontalBar data={this.state.chartData} width={100} height={210} options={{
                    maintainAspectRatio: false
                  }}
                  />
                </Box>
              </Row>
                <Row>
                <Box cols="12 12" title="REGISTRATION STATS - [ 2018 ]" icon="bar-chart" type="primary" collapsable>
                    <Line data={this.state.regData} width={200} height={210} options={this.state.regOptions} />
                </Box>
                </Row>

                <Row>
                    <Box cols="12 12" title="RECENTLY ADDED" icon="user" type="warning" solid collapsable>
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

            </Grid>
          </Row>


          {/*<Row>*/}
            {/*<Grid cols="12 8">*/}
              {/*<Row>*/}
                {/*<Box cols="12 12" icon="edit" title="LIST OF CUSTOMERS" type="primary" collapsable>*/}


                {/*</Box>*/}
              {/*</Row>*/}





            {/*</Grid>*/}
            {/*<Grid cols="12 4">*/}
              {/*<Row>*/}
                {/*<Box cols="12 12" title="GENERAL STATS - 2018" type="warning" icon="tag" collapsable>*/}

                  {/*<BoxHeader title="Best Customer" small="[ Total Income ]" />*/}
                  {/*<Grid cols="12 6">*/}
                    {/*<p className="text-light-blue" style={{ fontSize: 17 }}>João Dias</p>*/}
                    {/*<p className="text-purple">Rogerio Matias</p>*/}
                    {/*<p className="text-purple" style={{ fontSize: 13 }}>Pedro Nuno</p>*/}
                  {/*</Grid>*/}
                  {/*<Grid cols="12 6">*/}
                    {/*<p className="text-light-blue pull-right">104 €</p>*/}
                    {/*<Row />*/}
                    {/*<p className="text-purple pull-right">94 €</p>*/}
                    {/*<Row />*/}
                    {/*<p className="text-purple pull-right">88 €</p>*/}
                  {/*</Grid>*/}


                  {/*<Row />*/}
                  {/*<BoxHeader title="Show Up Longest Period" small="[ Last Sale ]" smallTwo="[ Sales ]" />*/}
                  {/*<Grid cols="12 6">*/}
                    {/*<p className="text-light-blue" style={{ fontSize: 17 }}>Pedro Pereira</p>*/}
                    {/*<p className="text-purple">Filipe Sousa</p>*/}
                    {/*<p className="text-purple" style={{ fontSize: 13 }}>Ruben Filipe</p>*/}
                  {/*</Grid>*/}
                  {/*<Grid cols="12 6">*/}
                    {/*<p className="text-light-blue pull-right">226 dias</p>*/}
                    {/*<Row />*/}
                    {/*<p className="text-purple pull-right">191 dias</p>*/}
                    {/*<Row />*/}
                    {/*<p className="text-purple pull-right">164 dias</p>*/}
                  {/*</Grid>*/}


                {/*</Box>*/}
              {/*</Row>*/}
              {/*<Row>*/}
                {/*<Box cols="12 12" title="GENERAL STATS - ALL TIME" icon="tag" type="warning" collapsable>*/}

                  {/*<BoxHeader title="Best Customer" small="[ Total Income ]" />*/}
                  {/*<Grid cols="12 6">*/}
                    {/*<p className="text-light-blue" style={{ fontSize: 17 }}>João Dias</p>*/}
                    {/*<p className="text-purple">Rogerio Matias</p>*/}
                    {/*<p className="text-purple" style={{ fontSize: 13 }}>Vitor Bruno</p>*/}
                  {/*</Grid>*/}
                  {/*<Grid cols="12 6">*/}
                    {/*<p className="text-light-blue pull-right">324 €</p>*/}
                    {/*<Row />*/}
                    {/*<p className="text-purple pull-right">294 €</p>*/}
                    {/*<Row />*/}
                    {/*<p className="text-purple pull-right">274 €</p>*/}
                  {/*</Grid>*/}
                  {/*<Row />*/}
                  {/*<BoxHeader title="Show Up Longest Period" small="[ Last Sale ]" />*/}
                  {/*<Grid cols="12 6">*/}
                    {/*<p className="text-light-blue" style={{ fontSize: 17 }}>Pedro Almeida</p>*/}
                    {/*<p className="text-purple">Sandro Almeida</p>*/}
                    {/*<p className="text-purple" style={{ fontSize: 13 }}>Pedro Pereira</p>*/}
                  {/*</Grid>*/}
                  {/*<Grid cols="12 6">*/}
                    {/*<p className="text-light-blue pull-right">526 dias</p>*/}
                    {/*<Row />*/}
                    {/*<p className="text-purple pull-right">491 dias</p>*/}
                    {/*<Row />*/}
                    {/*<p className="text-purple pull-right">326 dias</p>*/}
                  {/*</Grid>*/}

                {/*</Box>*/}
              {/*</Row>*/}




            {/*</Grid>*/}

          {/*</Row>*/}
          {/* <PageHeader title="Additional Information"/>  */}

          <PageHeader />


        </Content>
      </div>
    )
  }
}