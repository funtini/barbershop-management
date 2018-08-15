import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import SmallBox from '../../common/widget/smallBox'
import Row from '../../common//layout/row'



export default class Dashboard extends Component {
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