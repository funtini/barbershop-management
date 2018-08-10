import React from 'React'

export default props => (
    <div className={`progress ${props.vertical?'vertical':null} progress-${props.size} ${props.stripped?'progress-striped':''} ${props.active?'active':''}`}>
    <div className={`progress-bar progress-bar-${props.type}`} style={props.vertical? {height: `${props.progress}%`}:{width: `${props.progress}%`}} ></div>
    </div>
    // style={{width: `${props.progress}%`}}
)