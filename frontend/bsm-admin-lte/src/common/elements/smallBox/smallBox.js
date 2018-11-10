import React from 'React'
import Grid from '../../layout/grid'

import './smallBox.css'

/**
 * icon -> fontawswome extension
 * footerTitle -> title strong
 * footerText -> just text 
 * variance -> value (just can be used without footerText)
 * caret -> 'up','down' or 'right'  (just can be used without footerText)
 */

export default props => (
    <Grid cols={props.cols}>
        <div className={`small-box bg-${props.color}`}>
            <div className='inner'>
                <h3>{props.value}</h3>
                <p>{props.text}</p>
            </div>
            <div className='icon'>
                <i className={`fa fa-${props.icon}`}/>
            </div>
            <div className="small-box-footer">
            <strong>{props.footerTitle} </strong>
            {   props.footerText ? 
            props.footerText : 
            <span className="lead">{props.variance}% <i className={`fa fa-caret-${props.caret}`}/> </span>
            }
            </div>
        </div>
    </Grid>
)