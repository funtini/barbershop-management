import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import SmallBox from '../../common/widget/smallBox'
import Row from '../../common/layout/row'
import RadioGroupButton from '../../common/radioButtonGroup'
import ToggleSwitch from '../../common/toggleSwitch/toggleSwitch'
import './settings.css'

export default class Settings extends Component {
    render() {
        
        return (
            <div> 
                <ContentHeader title='Settings' small='Today Summary'/>
                <Content>
                     <Row>
                     <RadioGroupButton/>
                     <ToggleSwitch/>
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