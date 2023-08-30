import React from 'react';
import { tabSidebarTextColor, tabSidebarBGActiveColor, tabSidebarBGInactiveColor } from '../colors';

const TabsSidebar = ({ tabs, activeTab, onTabClick }) => {
  return (
    <div style={{ width: '200px', height: '60px', position: 'sticky', top: 0}}>
        <div style={{color: 'lightblue'}}>
            <div style={{marginTop: '0px'}}>
            {tabs.map((tab, index) => (
                <button
                key={index}
                onClick={() => onTabClick(tab.value)}
                style={{
                    width: '100%', 
                    height: '60px',
                    marginBottom: '0px', 
                    backgroundColor: activeTab === tab.value ? tabSidebarBGActiveColor : tabSidebarBGInactiveColor,
                    borderRightColor: 'black',
                    color: activeTab === tab.value ? 'white' : tabSidebarTextColor,
                    boxShadow: activeTab === tab.value ? 'none' : '0px 2px 4px rgba(0, 0, 0, 0.4)', 
                    zIndex: activeTab === tab.value ? '0' : '1', 
                    borderColor: activeTab === tab.value ? 'transparent' : 'black'
                }}
                >
                {tab.label}
                </button>
            ))}
            </div>
      </div>
    </div>
  );
};

export default TabsSidebar;
