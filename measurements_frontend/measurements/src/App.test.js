import { render, screen } from '@testing-library/react';
import React from 'react';
import ProductsTable from './components/Products/ProductsTable';


it('checks if empty products table renders', () => {
  const products = [];
  render(<ProductsTable products={products} />);
  expect(screen.getByText('name')).toBeInTheDocument();
});
