import React, { useState } from 'react';
import { connect } from 'react-redux';

// template components
import Header from 'template/header';
import SideBar from 'template/sidebar';
import Footer from 'template/footer';

//utils
import joinClassNames from 'shared/utils/joinClassNames';

// styles
import styles from './Layout.css';
import { expandSidebar } from 'shared/state/layout';

const layout = ( props ) => {
        const [toggle, setToggle] = useState(false);

        console.log(toggle)

        return (
            <div className={ styles.wrapper }>
                    <Header className={ styles.header } onToggleClick={ () => {
                        setToggle(!toggle);
                        _handleToggleClick();
                        props.collapseSidebar();
                    }}/>
                    <SideBar className={ joinClassNames( styles.sidebar, toggle && styles.collapsed )} />
                    <main className={ joinClassNames( styles.content, toggle && styles.expand ) }>
                            <div className={ styles.contentWrapper }>
                                    { props.children }
                            </div>
                    </main>
                    <Footer className={ joinClassNames( styles.footer, toggle && styles.expand) } />
            </div>
        );
}

const _handleToggleClick = () => (
    console.log("IM CLICKING")

);

const mapDispatchToProps = (dispatch) => ({
    collapseSidebar: () => dispatch(expandSidebar())
});

export default connect(null,mapDispatchToProps)(layout);
