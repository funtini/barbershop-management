import React from 'React'

export default props => (
    <button type="button" className={`btn ${props.block?'btn-block':null} btn-${props.type} btn-${props.size}
     ${props.disabled?'disabled':null} ${props.pullright?'pull-right':''}`} onClick={()=>props.onClick()} style={props.style}>
    {props.name}
    </button>
  
)