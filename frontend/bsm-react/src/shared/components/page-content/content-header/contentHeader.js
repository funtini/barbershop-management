import React from 'react'

//components
import BreadCrumb from './bread-crumb/breadCrumb';

//styles
import styles from './ContentHeader.css';

const contentHeader = ( props ) => (
    <section className={ styles.contentHeader }>
        <h1>{props.title} <small>{props.small}</small></h1>
        <BreadCrumb/>
    </section>
);

export default contentHeader;