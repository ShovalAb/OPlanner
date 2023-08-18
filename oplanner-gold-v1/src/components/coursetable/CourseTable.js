import React from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, colors } from '@mui/material';

const CourseTable = ({ data, onRowClick }) => {
  const maxHeight = 200; // Set the desired fixed height
  const rowHeight = 60; // Set the height of a single row (adjust as needed)
  const headRowHeight = 70;

  const shouldRenderEmptySpace = data.length * rowHeight + headRowHeight< maxHeight;

  return (
    <Paper style={{ maxHeight: `${maxHeight}px`, overflowY: 'auto' }}>
      <Table aria-label="Beautiful Table">
        <TableHead>
          <TableRow style={{position: 'sticky', top: 0, zIndex: 1, height: `${headRowHeight}px`, background: '#e0e0e0'}}>
            <TableCell style={{ color: '#333', fontWeight: 'bold' }}>Course Name</TableCell>
            <TableCell style={{ color: '#333', fontWeight: 'bold' }}>Course Number</TableCell>
            <TableCell style={{ color: '#333', fontWeight: 'bold' }}>Credits Number</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map((row, index) => (
            <TableRow key={index} onClick={() => onRowClick(row)} style={{height: `${rowHeight}px`, background: index % 2 === 0 ? '#f5f5f5' : 'white'}}>
              <TableCell>{row.courseName}</TableCell>
              <TableCell>{row.courseNumber}</TableCell>
              <TableCell>{row.creditsNumber}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      {/* Conditionally render empty space only if needed */}
      {shouldRenderEmptySpace && (
        <div style={{ height: `${maxHeight - headRowHeight - (data.length * rowHeight)}px` }} />
      )}
    </Paper>
  );
}

export default CourseTable;
