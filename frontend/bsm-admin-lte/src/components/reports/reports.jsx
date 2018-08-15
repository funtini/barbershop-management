import React, { Component } from 'react'
import ContentHeader from '../../common/template/content/contentHeader';
import PageHeader from '../../common/template/content/pageHeader'
import Content from '../../common/template/content/content'
import Grid from '../../common//layout/grid'
import InfoBox from '../../common/widget/infoBox'
import Row from '../../common//layout/row'

export default class Reports extends Component {
    constructor(props) {
        super(props);

    };


    render() {

        return (
            <div>
                <ContentHeader title='Reports' small='Summary' />
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

                </Content>
            </div>
        )
    }
}