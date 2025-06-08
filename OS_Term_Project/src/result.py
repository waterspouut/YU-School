def report_results(algorithm_name, final_frames, page_faults, page_hits, reference_list, second_chance_info=None, frame_history=None):
    total_references = len(reference_list)
    page_fault_rate = (page_faults / total_references) * 100

    fault_delay = page_faults * 10 
    hit_delay = page_hits * 0.1    
    if algorithm_name == "Second Chance":
        bit_delay = page_faults * 0.1
        total_delay = fault_delay + hit_delay + bit_delay
    else:
        total_delay = fault_delay + hit_delay

    # EAT
    memory_access_time = 0.1
    page_fault_time = 10
    p = page_fault_rate / 100
    eat = (1 - p) * memory_access_time + p * page_fault_time

    print(f"\n{algorithm_name} 알고리즘 결과:")
    
    if frame_history:
        print("\n페이지 참조 진행 상황:")
        print("단계 | 참조 페이지 | 프레임 상태 | 결과")
        print("-" * 60)
        for step, (page, frames, is_fault) in enumerate(frame_history, 1):
            if is_fault:
                result = "페이지 폴트"
            else:
                result = "페이지 히트"
            frames_str = str(frames).replace(" ", "")
            print(f"{step:^4} | {page:^10} | {frames_str:^20} | {result}")
    
    print(f"\n최종 프레임 상태: {final_frames}")
    print(f"페이지 폴트 횟수: {page_faults}")
    print(f"페이지 히트 횟수: {page_hits}")
    print(f"총 참조 횟수: {total_references}")
    print(f"페이지 폴트율: {page_fault_rate:.2f}%")
    
    if algorithm_name == "Second Chance" and second_chance_info:
        print("\nSecond Chance 특성:")
        print("두 번째 기회를 얻은 페이지:")
        for page, time in second_chance_info:
            print(f"  - 페이지 {page}: {time}번째 참조에서 두 번째 기회 획득")
    
    print(f"페이지 폴트로 인한 지연시간: {fault_delay:.1f}ms")
    print(f"페이지 히트로 인한 지연시간: {hit_delay:.1f}ms")
    if algorithm_name == "Second Chance":
        print(f"비트 지연시간: {bit_delay:.1f}ms")
    print(f"총 걸린 시간: {total_delay:.1f}ms")
    print(f"\n유효 접근 시간(EAT):")
    print(f"EAT = (1-p) × 메모리 접근 시간 + p × 페이지 폴트 서비스 시간")
    print(f"EAT = (1-{p:.3f}) × {memory_access_time}ms + {p:.3f} × {page_fault_time}ms = {eat:.3f}ms") 