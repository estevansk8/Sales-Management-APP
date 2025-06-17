package org.sales.management.sales.presentation.form

import androidx.lifecycle.ViewModel
import org.sales.management.sales.domain.repository.SaleRepository

class SaleFormsViewModel(
    private val repository: SaleRepository
): ViewModel()  {
}