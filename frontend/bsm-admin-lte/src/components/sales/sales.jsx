import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import Content from '../../common/template/content/content'
import External from '../../components/ui/external'
import Calen from '../../components/ui/calen'
import Row from '../../common//layout/row'
import Grid from '../../common//layout/grid'
import Box from '../../common/elements/box'
// import AddExternal from '../../components/ui/addExternal'
import './sales.css'

export default class Sales extends Component {

    
  
    render() {
        
        
        return (
            <div> 
                <ContentHeader title='Sales'/>
                <Content>
                <Row>
                
                <Box cols="12 3" title="Draggable Events" type="primary">
                <External/>
               
                </Box>
                <Grid cols="12 8">
           
                <div className="box box-primary">
                    <div className="box-body no-padding">
             
                    <Calen/>
                    </div>
      
                </div>
                </Grid>
                {/* <Box cols="12 7" title="Calendar" type="primary" collapsable>
                <Calen/>
                </Box> */}
            
                </Row>
                <Row>
                <Box cols="12 3" title="Draggable Events" type="primary">
                {/* <AddExternal/> */}
               
                </Box>
                </Row>
                
               
                
                </Content>
            </div>
        )
    }
}