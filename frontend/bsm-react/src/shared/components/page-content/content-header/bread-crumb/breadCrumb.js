import React from 'react'

//styles
import styles from './breadCrumb.css';

const breadCrumb = ( props ) => (
    <ol className={ styles.breadCrumb }>
        <li>
            <a href="#"><i className="fa fa-dashboard" /> Home</a>
        </li>
        <li className={ styles.active }>Dashboard</li>
    </ol>

);

export default breadCrumb;