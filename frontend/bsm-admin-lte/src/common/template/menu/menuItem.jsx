import React from 'react'

export default props => (
    <li> 
        <a href={props.path}>
            <i className={`fa fa-${props.icon}`}/>
            <span>
                {props.label}
            </span>
            <span className="pull-right-container">
                {props.children}
            </span>
        </a>
    </li>
)