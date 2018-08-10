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



export default class Products extends Component {
    constructor(props) {
        super(props);
        this.state = {themeChange: false};

        // this.handleChangeTheme = this.handleChangeTheme.bind(this);
      }

    
    

    // handleChangeTheme(){
    //     this.setState({themeChange:!this.state.themeChange})

    // }

    render() {
        var dataHeader = [ "Um","Dois","Tres","Quatro","Quinta"]
        
        return (
            <div> 
                <ContentHeader title='Products' small='Summary'/>
                
                <Content><div style={{borderTop:'1px solid rgb(180, 180, 180)'}}/><br/>{this.state.themeChange?
                
                    <Row>
                    
                        <SmallBox cols='12 3' color='blue' icon='bank'
                            value='87' text='Total Customers' footer="Last Month: 83 (+5%)" />
                        <SmallBox cols='12 3' color='blue' icon='credit-card'
                            value='5' text='Added this month' footer="Average: 5 (+0%)" />
                        <SmallBox cols='12 3' color='blue' icon='money'
                            value='22,5' text='Average Age' footer="Last Month: 23 (-0.5%)" /> 
                        <SmallBox cols='12 3' color='green' icon='money'
                            value='Mangualde' text='Average Location' footer="Number of Customers: 55" /> 
                    </Row> : <Row>
                   
                     <ValueBox cols='12 3' color='aqua-active' icon='archive'
                            value='8' text='Total' progress='5' />
                        <InfoBox cols='12 3' color='aqua-active' icon='credit-card'
                            value='5' text='Added this month' progress='5'/>
                        <InfoBox cols='12 3' color='aqua-active' icon='money'
                            value='22,5' text='Average Age' progress='0' /> 

                            <InfoBox cols='12 3' color='aqua-active' icon='money'
                            value='Mangualde' text='Average Location' progress='20' />
                            

                    </Row>   
                    }
                    
                    {/* <Switch checked={this.state.themeChange} label="Change Theme" onChange={this.handleChangeTheme}/> */}
                    <div style={{borderTop:'1px solid rgb(180, 180, 180)'}}/><br/>
                    {/* <PageHeader /> */}
                    
                    <Row>
                    <Box cols="12 5" title="NEW PRODUCT" icon="edit" type="primary" collapsable solid>
                    
                        <InputBox type="text" label="Name" placeholder="Enter Name" cols="6" />
                        <InputBox type="text" label="Phone" placeholder="Enter Phone Number"  width="40"/>
                        <InputBox type="text" label="Birth Date" placeholder="YYYY/MM/DD"  cols="6"/>
                        <InputBox type="text" label="Address" placeholder="Enter Location Name"  width="40"/>
               
          
                 
           
                       
                 
                        
                        <div className="box-footer">
                        <Button type="primary" name="ADD CUSTOMER" className="btn-add-customer"/> 
                        <Button  name="Clear Fields" className="btn-add-customer" pullright/> 
                        </div>
       
                        </Box>
                        <Box cols="12 3" title="RECENTLY ADDED" icon="edit" type="primary" collapsable>
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
                                    <div className="product-title text-blue">Pedro Sousa <span className="label label-success pull-right">1d</span></div>
                                    <span className="product-description">
                                    Mesquitela
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
                    {/* <PageHeader title="Additional Information"/>  */}
                    <Row> 
                    <Box cols="12 8" title="LIST OF PRODUCTS" type="primary" collapsable>
                
                
                    <DataTable  cols='12' column={dataHeader}/>
                
                    </Box> 
                    <Box cols="12 4" title="STATISTICS" type="primary" collapsable>
                
                
                    
                
                    </Box> 
                    </Row>
                    <Row>
                    <Box cols="12 8" title="PRODUCTS GRAPH" type="primary" collapsable>
                
        
                    
                        </Box> 
                    </Row>  
                   
                    
     
                    <PageHeader/> 
              
                    
                </Content>
            </div>
        )
    }
}