import React from 'react'

// styles
import styles from './Content.css';

const content = ( props ) => (
    <section className={ styles.content }>
        {props.children}
    </section>
);

export default content;