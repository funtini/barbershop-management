import React, { Component } from 'react'
import ContentHeader from '../../common/template/contentHeader';
import Content from '../../common/template/content'
import ValueBox from '../../common/widget/valueBox'
import Row from '../../common//layout/row'


export default class Dashboard extends Component {
    render() {
        
        return (
            <div> 
                <ContentHeader title='Dashboard' small='version 1.0'/>
                <Content>
                    <Row>
                        <ValueBox cols='12 4' color='green' icon='bank'
                            value='10 €' text='Income' />
                        <ValueBox cols='12 4' color='red' icon='credit-card'
                            value='5 €' text='Expenses' />
                        <ValueBox cols='12 4' color='blue' icon='money'
                            value='5 €' text='Profit/Loss' /> 
                    </Row>  
                </Content>
            </div>
        )
    }
}