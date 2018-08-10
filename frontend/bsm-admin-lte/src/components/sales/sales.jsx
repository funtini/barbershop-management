import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import External from '../../components/ui/external'
import Calen from '../../components/ui/calen'
import './sales.css'

export default class Sales extends Component {

    

    render() {
        
        return (
            <div> 
                <ContentHeader title='Sales'/>
                <Content>
                <External/>
                <Calen/>
                
                </Content>
            </div>
        )
    }
}