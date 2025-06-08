from algorithms import fifo, lru, second_chance

def get_algorithm_choice():
    print("\n사용 할 페이지 교체 알고리즘:")
    print("1. FIFO")
    print("2. LRU")
    print("3. Second Chance")

    while True:
        choice = int(input("\n알고리즘 번호를 입력하세요 (1-3): "))
        if choice == 1:
            return "FIFO", fifo
        elif choice == 2:
            return "LRU", lru
        elif choice == 3:
            return "Second Chance", second_chance
        else:
            print("1, 2, 3 중에서 선택해주세요.")