import React from 'React'
import './palette.css'

export default props => (
        <div className={`bg-${props.color} ${props.disabled?"disabled":null} color-palette`} style={props.centered ? { textAlign: "center", paddingLeft: 0 } : null}>
            <span>{props.text}</span>
        </div>
  
)