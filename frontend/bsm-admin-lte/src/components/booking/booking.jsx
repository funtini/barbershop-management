import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import Grid from '../../common//layout/grid'
import InfoBox from '../../common/widget/infoBox'
import Row from '../../common//layout/row'
import Tooltip from '../../common/UI/tooltip'

export default class Booking extends Component {
    constructor(props) {
        super(props);

    };


    render() {

        return (
            <div>
                <ContentHeader title='Booking' small='Summary' />
                <Content><div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} />
                <Row>
                <Grid cols="12 6">
                <br />
                    <InfoBox cols='12 6' color='green' icon='calendar-plus-o'
                        value='16' text='Total Bookings'  />
                    <InfoBox cols='12 6' color='aqua-active' icon='tasks'
                        value='7' text='Today' footer={<span><strong>Next: </strong>14h30</span>}/>
                </Grid>
                    </Row>

<div className='container' >
        <p>Here is a <Tooltip message={'Hello, I am a super cool tooltip'} position={'top'}>tooltip</Tooltip> on top.</p>
        <p>Here is a <Tooltip message={'Hello, I am a super cool tooltip'} position={'bottom'}>tooltip</Tooltip> on bottom.</p>
        <p>Here is a <Tooltip message={'Hello, I am a super cool tooltip'} position={'left'}>tooltip</Tooltip> on left.</p>
        <p>Here is a <Tooltip message={'Hello, I am a super cool tooltip'} position={'right'}>tooltip</Tooltip> on right.</p>
      </div>
                </Content>
            </div>
        )
    }
}