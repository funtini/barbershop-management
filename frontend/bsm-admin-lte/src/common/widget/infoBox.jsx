import React from 'React'
import Grid from '../layout/grid'

export default props => (
    <Grid cols={props.cols}>
        <div className={`info-box bg-${props.color}`}>
        <span className="info-box-icon"><i className="fa fa-bookmark-o"></i></span>
            <div className='info-box-content'>
                <span className="info-box-text">{props.text}</span>
                <span className="info-box-number">{props.value}</span>
            </div>
            <div className="progress">
            <div className="progress-bar" style={{width:`${props.progress}%`}}></div>
            </div>
            <span className="progress-description" style={{paddingLeft:'1em'}}>
                    {props.progress}% Increase in 30 Days
            </span>
        </div>
    </Grid>
)