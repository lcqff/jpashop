
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "트랙 (Track)")
public class TrackController {

    private final TrackService trackService;

    /**
     * @RequestPart를 사용하는 경우 Swagger에서 정상적으로 파라미터를 인식하지 못함.
     * @ModelAttribute를 대신 사용하자
     */
    @Operation(summary = "트랙 업로드")
    @PostMapping(value = "album/{uuid}/track")
    public ResponseEntity<Void> uploadTrack(@ModelAttribute @Valid TrackRequestDto requestDto,
                                            @PathVariable String uuid) {
        trackService.save(requestDto, uuid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "트랙 조회")
    @GetMapping("/track/{uuid}")
    public ResponseEntity<TrackResponseDto> getByUuid(@PathVariable String uuid) {
        TrackResponseDto responseDto = trackService.getTrack(uuid);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "트랙 수정")
    @PatchMapping("/track/{uuid}")
    public ResponseEntity<Void> updateByUuid(@RequestBody @Valid TrackUpdateRequestDto requestDto,
                                             @PathVariable String uuid) {
        trackService.updateByUuid(requestDto, uuid);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "트랙 삭제")
    @DeleteMapping("/track/{uuid}")
    public ResponseEntity<Void> deleteByUuid(@PathVariable String uuid) {
        trackService.deleteByUuid(uuid);
        return ResponseEntity.noContent().build();
    }
}
