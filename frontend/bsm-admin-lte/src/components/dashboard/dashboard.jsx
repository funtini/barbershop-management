import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import ValueBox from '../../common/widget/valueBox'
import SmallBox from '../../common/widget/smallBox'
import InfoBox from '../../common/widget/infoBox'
import SimpleBox from '../../common/widget/simpleBox'
import Row from '../../common//layout/row'
import UserInfo from '../../common/widget/userInfo';


export default class Dashboard extends Component {
    render() {
        
        return (
            <div> 
                <ContentHeader title='Dashboard' small='version 1.0'/>
                <Content>
                    <Row>
                        <ValueBox cols='12 4' color='blue' icon='bank'
                            value='10 €' text='Income' />
                        <ValueBox cols='12 4' color='red' icon='credit-card'
                            value='5 €' text='Expenses' />
                        <ValueBox cols='12 4' color='green' icon='money'
                            value='5 €' text='Profit/Loss' /> 
                    </Row>

                     <Row>
                        <SmallBox cols='12 3' color='blue' icon='bank'
                            value='10 €' text='Income' />
                        <SmallBox cols='12 3' color='red' icon='credit-card'
                            value='5 €' text='Expenses' />
                        <SmallBox cols='12 3' color='green' icon='money'
                            value='5 €' text='Profit/Loss' /> 
                    </Row>   

                    <Row>
                        <InfoBox cols='12 3' color='aqua' icon='bank'
                            value='10 €' text='Income' progress='70' />
                        <InfoBox cols='12 3' color='red' icon='credit-card'
                            value='5 €' text='Expenses' progress='50'/>
                        <InfoBox cols='12 3' color='green' icon='money'
                            value='5 €' text='Profit/Loss' progress='90' /> 

                            <InfoBox cols='12 3' color='green' icon='money'
                            value='5 €' text='Profit/Loss' progress='20' /> 

                    </Row>  

                    <Row>
                        <SimpleBox cols='12 3' color='aqua' icon='bank'
                            value='10 €' text='Income' progress='70' />
                        <SimpleBox cols='12 3' color='red' icon='credit-card'
                            value='5 €' text='Expenses' progress='50'/>
                        <SimpleBox cols='12 3' color='green' icon='money'
                            value='5 €' text='Profit/Loss' progress='90' /> 

                        <SimpleBox cols='12 3' color='green' icon='money'
                            value='5 €' text='Profit/Loss' progress='20' /> 

                    </Row>
                     
              
                    <Row>
                        <UserInfo cols='12 6 4' color='aqua-active' name="Joao Gomes" role="Store Manager"/>
                        <UserInfo cols='12 6 4' color='aqua-active' name="Joao Gomes" role="Store Manager"/>
                        <UserInfo cols='12 6 4' style="2" color='aqua-active' name="Joao Gomes" role="Store Manager"/>
                    </Row>
     
                    <PageHeader/> 
              
                    
                </Content>
            </div>
        )
    }
}