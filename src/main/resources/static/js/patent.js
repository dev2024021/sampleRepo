const originalForm = document.getElementById("originalForm");
const patentIdInput = document.getElementById("patentId");
const originalFrame = document.getElementById("originalFrame");
const originalStatus = document.getElementById("originalStatus");

async function showOriginal(patentId) {
    originalStatus.textContent = "원문 주소를 요청하고 있습니다.";

    try {
        const query = new URLSearchParams({ patentId });
        const response = await fetch(`/api/patents/original?${query}`);
        if (!response.ok) {
            throw new Error(`원문 요청 실패: ${response.status}`);
        }

        const data = await response.json();
        const originalUrl = new URL(data.originalUrl, window.location.origin);
        if (originalUrl.origin !== window.location.origin) {
            throw new Error("허용되지 않은 원문 주소입니다.");
        }

        originalFrame.src = originalUrl.href;
        originalStatus.textContent = `${patentId} 원문을 표시했습니다.`;
    } catch (error) {
        originalFrame.removeAttribute("src");
        originalStatus.textContent = "원문을 불러오지 못했습니다.";
        console.error(error);
    }
}

originalForm.addEventListener("submit", (event) => {
    event.preventDefault();
    if (originalForm.reportValidity()) {
        showOriginal(patentIdInput.value);
    }
});
