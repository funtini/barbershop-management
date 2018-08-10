import React from 'React'

export default props => (
    <span className={`badge bg-${props.colour}`}>{props.text}</span>
)