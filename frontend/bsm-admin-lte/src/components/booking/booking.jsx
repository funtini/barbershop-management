import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import Grid from '../../common//layout/grid'
import InfoBox from '../../common/widget/infoBox'
import Row from '../../common//layout/row'
import Tooltip from '../../common/UI/tooltip'
import Calendar from '../../common/eventCalendar/calendar'
import Box from "../../common/elements/box";
import Avatar from 'react-avatar';

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
                    <Row>
                    <InfoBox cols='12 6' color='green' icon='calendar-plus-o'
                        value='16' text='Total Bookings'  />
                    <InfoBox cols='12 6' color='aqua-active' icon='tasks'
                        value='7' text='Today' footer={<span><strong>Next: </strong>14h30</span>}/>
                    </Row>
                    <div style={{ borderTop: '1px solid rgb(180, 180, 180)' }} /><br />
                    <Row>

                        <Box cols="12 12" type="info" noPadding title="SCHEDULE" solid centeredTitle style={{paddingBottom: '50px'}}>
                            <Calendar noButtons
                                      height="auto"
                                      slotDuration='00:30:00'
                                      defaultView='listDay'
                                      header={{
                                          left: 'prev,next today',
                                          center: 'title',
                                          right: 'listWeek, listDay'
                                      }}
                            />

                        </Box>
                    </Row>
                </Grid>
                    </Row>


<div className='container' >
    <Avatar name="Pedro Domingos" textSizeRatio="1.50" size="30" round={true}/>
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