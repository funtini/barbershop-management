import React from 'React'

export default props => (
    <div className={`btn-group${props.vertical?'-vertical':''}`}>{props.children}
    </div>
  
)