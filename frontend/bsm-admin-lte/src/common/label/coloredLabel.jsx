import React from 'React'

export default props => (
    <span className={`label label-${props.type}`}>{props.text}</span>
)