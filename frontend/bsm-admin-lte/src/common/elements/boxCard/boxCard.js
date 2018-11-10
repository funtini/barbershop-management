import React from 'react';
import Grid from '../../layout/grid'

import './boxCard.css'


export default props => (props.cols?
  <Grid cols={props.cols}>
    <div className="box_card" { ...props }>
        <h2 className="box_card--title">{props.title}</h2>
        {props.children} 
    </div>
  </Grid> : <div className="box_card" { ...props }>
        <h2 className="box_card--title">{props.title}</h2>
        {props.children} 
    </div>
)
