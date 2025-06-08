def get_frame_size():
    while True:
        frame_size = int(input("\n프레임 크기를 입력하세요: "))
        if frame_size <= 0:
            print("프레임 크기는 양수여야 합니다.")
            continue
        return frame_size

def get_reference_list():
    while True:
        reference_string = input("\n참조 문자열을 입력하세요 (예: 123412512345): ")
        if not reference_string.isdigit():
            print("참조 문자열은 숫자로만 이루어져야 합니다.")
            continue
        reference_list = []
        for digit in reference_string:
            reference_list.append(int(digit))
        return reference_list