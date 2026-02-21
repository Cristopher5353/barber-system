$(document).ready(function() {
  let tableSupplier = $('#myDataTableSupplier').DataTable({
    responsive: true,
    scrollX: true,
  });

  tableSupplier.columns.adjust().draw();

  let tableProduct = $("#myDataTableProduct").DataTable({
    responsive: true,
    scrollX: true,
  });

  tableProduct.columns.adjust().draw();
  
  tableSupplier.on('draw', function() {
    $("input[name='supplierId']").prop('checked', false);
  });

  tableProduct.on('draw', function() {
    $("input[name='productId']").prop('checked', false);
  });
})

document.addEventListener("click", (e) => {
  if (e.target.matches(".toggle-sidebar-btn")) {
    document.getElementById("body").classList.toggle("toggle-sidebar");
  }

  if (e.target.matches("#btn-logout *")) {
    document.getElementById("btn-form-logout").click();
  }
});