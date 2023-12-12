document.addEventListener("DOMContentLoaded", function () {
    var modal = document.getElementById("myModal");
    var span = document.getElementsByClassName("close")[0];

    document.querySelectorAll(".open-modal-btn").forEach(function (button) {
        button.addEventListener("click", function () {
            openModal(button);
        });
    });


    span.addEventListener("click", closeModal);
    window.addEventListener("click", function (event) {
        if (event.target === modal) {
            closeModal();
        }
    });

    function openModal(button) {
        var reviewId = button.getAttribute("data-review-id");
        var reviewBrewery = button.getAttribute("data-review-brewery");
        var reviewDescription = button.getAttribute("data-review-description");


        document.getElementById("editedBrewery").value = reviewBrewery;
        document.getElementById("editedDescription").value = reviewDescription;


        modal.style.display = "block";
    }

    function closeModal() {
        modal.style.display = "none";
    }

    var editPostForm = document.getElementById("editPostForm");
    editPostForm.addEventListener("submit", function (event) {
        event.preventDefault();

        closeModal();
    });
});

