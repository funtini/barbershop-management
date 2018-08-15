import React, { Component } from 'react'
import ContentHeader from '../../../common/template/content/contentHeader';
import PageHeader from '../../../common/template/content/pageHeader'
import Content from '../../../common/template/content/content'
import SmallBox from '../../../common/widget/smallBox'
import Row from '../../../common//layout/row'



export default class DashboardBusiness extends Component {
    render() {
        
        return (
            <div> 
                <ContentHeader title='Dashboard' small='Business Summary'/>
                <Content>
                     <Row>
                        <SmallBox cols='12 3' color='aqua' icon='bank'
                            value='186' text='Sales per Month'  footerText={<span className="lead">-5% <i className={`fa fa-caret-down`}/> </span>} />
                        <SmallBox cols='12 3' color='green' icon='euro'
                            value='1355 €' text='Income per Month' footerText={<span className="lead">+8% <i className={`fa fa-caret-up`}/> </span>} />
                        <SmallBox cols='12 3' color='yellow' icon='user-plus'
                            value='64' text='Customers Registered' footerText={<span className="lead">+12% <i className={`fa fa-caret-up`}/> </span>} />
                        <SmallBox cols='12 3' color='red' icon='calendar-minus-o'
                            value='327 €' text='Expenses Average' footerText={<span className="lead">+0% <i className={`fa fa-caret-right`}/> </span>}/>  
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