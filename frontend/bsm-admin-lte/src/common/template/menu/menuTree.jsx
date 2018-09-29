import React from 'react'
import "./menuTree.css"

export default props => (
    <li data-animation-speed="0.3" className='treeview'> 
        <a href={props.path}> 
            <i className={`fa fa-${props.icon}`}></i>
            <span>{props.label}</span>
            <span className="pull-right-container">
                <i className='fa fa-angle-left pull-right'></i>
            </span>
        </a>
        <ul className='treeview-menu'> 
            {props.children}
        </ul>
    </li>
)