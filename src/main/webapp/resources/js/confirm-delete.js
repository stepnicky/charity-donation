const deleteButtons = document.querySelectorAll(".delete");
const confirmDelete = document.querySelector(".confirm-delete");

deleteButtons.forEach(deleteButton => {
  deleteButton.addEventListener("click", e => {
    e.preventDefault();
    const href = e.target.getAttribute("href");
    console.log(href);
    confirmDelete.setAttribute("href", href);
  });
});
