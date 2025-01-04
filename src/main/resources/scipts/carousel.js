document.addEventListener('DOMContentLoaded', () => {
    const carouselInner = document.querySelector('.carousel-inner');
    const items = carouselInner.children;
    const prevButton = document.querySelector('.carousel-control-prev');
    const nextButton = document.querySelector('.carousel-control-next');
    let currentIndex = 0;

    const updateCarousel = () => {
        const offset = currentIndex * -100;
        carouselInner.style.transform = `translateX(${offset}%)`;
    };

    prevButton.addEventListener('click', () => {
        currentIndex = (currentIndex > 0) ? currentIndex - 1 : items.length - 1;
        updateCarousel();
    });

    nextButton.addEventListener('click', () => {
        currentIndex = (currentIndex < items.length - 1) ? currentIndex + 1 : 0;
        updateCarousel();
    });
});
