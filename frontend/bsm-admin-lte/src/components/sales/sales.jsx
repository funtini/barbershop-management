import React, { Component } from 'react'
import ContentHeader from '../../common/template/contentHeader';
import Content from '../../common/template/content'

export default class Sales extends Component {

    

    render() {
        
        return (
            <div> 
                <ContentHeader title='Sales'/>
                <Content>
                    New Sale
                </Content>
            </div>
        )
    }
}