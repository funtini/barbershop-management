import React from 'React'
import Grid from '../layout/grid'

export default props => (props.cols?<Grid cols={props.cols}>
        <label>{props.text}</label>
       </Grid> : <label>{props.text}</label>
)