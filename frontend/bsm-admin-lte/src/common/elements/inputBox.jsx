import React from 'React'
import Grid from '../layout/grid'
import Label from './label'

export default props => (props.cols?
  <Grid cols={props.cols}>
    <div className="form-group">
      {props.label?<Label text={props.label}/>:null}
    <input className={`form-control input-${props.size}`} type={`${props.type}`} placeholder={`${props.placeholder}`} value={props.value} onChange={()=>props.onChange()} style={{width:`${props.width}%`}} />
    </div>
  </Grid> : <div className="form-group">
  {props.label?<Label text={props.label}/>:null}
  <input className={`form-control input-${props.size}`} type={`${props.type}`} placeholder={`${props.placeholder}`} value={props.value} onChange={()=>props.onChange()} style={{width:`${props.width}%`}} />
  </div>
)