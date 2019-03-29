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
import { getSidebarStatus } from 'shared/state/layout/selectors';

const layout = ( props ) => {
        const { isCollapsed } = props;

        console.log(isCollapsed)

        return (
            <div className={ styles.wrapper }>
                    <Header className={ styles.header } onToggleClick={ () => props.collapseSidebar() }/>
                    <SideBar className={ joinClassNames( styles.sidebar, isCollapsed && styles.collapsed )} />
                    <main className={ joinClassNames( styles.content, isCollapsed && styles.expand ) }>
                            <div className={ styles.contentWrapper }>
                                    { props.children }
                            </div>
                    </main>
                    <Footer className={ joinClassNames( styles.footer, isCollapsed && styles.expand) } />
            </div>
        );
};

const mapDispatchToProps = (dispatch) => ({
    collapseSidebar: () => dispatch(expandSidebar())
});

const mapStateToProps = (state) => ({
    isCollapsed: getSidebarStatus(state),
});

export default connect(mapStateToProps,mapDispatchToProps)(layout);
